package com.cricket.local_score.Controller;

import com.cricket.local_score.Common.Enums.UserStatus;
import com.cricket.local_score.Configuration.JwtUtil;
import com.cricket.local_score.Entity.UserEntity;
import com.cricket.local_score.Repository.UserRepository;
import com.cricket.local_score.model.Auth.LoginRequest;
import com.cricket.local_score.model.Auth.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired private UserRepository userRepo;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private AuthenticationManager authManager;
    @Autowired private JwtUtil jwtUtil;

    @Autowired private OtpController otpController;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@Valid @RequestBody SignUpRequest signUpRequest) {

        if (userRepo.findByEmail(signUpRequest.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already taken");
        }

        ResponseEntity<String> responseEntity=otpController.verifyOtp(signUpRequest.getEmail(), signUpRequest.getOtp());

        if(!responseEntity.getStatusCode().is2xxSuccessful()){
            return responseEntity;
        }

        UserEntity user=UserEntity.builder()
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .fullName(signUpRequest.getFullName())
                .userStatus(UserStatus.ACTIVE)
                .build();

        userRepo.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody LoginRequest request) {

        try{
            Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(token);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException e) {
        StringBuilder errorMessage = new StringBuilder("Signup failed: ");

        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errorMessage.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage())
                    .append("; ");
        }

        return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/get-id-by-email")
    public ResponseEntity<?> getUserIdByEmail(@RequestParam String email) {
        Optional<UserEntity> userOptional = userRepo.findByEmail(email);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();

            Map<String, Object> response = new HashMap<>();
            response.put("userId", user.getUserId());
            response.put("fullName", user.getFullName()); // or build with user.getFirstName() + " " + user.getLastName()

            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>("User not found with email: " + email, HttpStatus.NOT_FOUND);
        }
    }



}
