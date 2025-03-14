package com.cricket.local_score.Controller;

import com.cricket.local_score.Entity.UserEntity;
import com.cricket.local_score.Repository.UserRepository;
import com.cricket.local_score.Service.UserService;
import com.cricket.local_score.model.Auth.LoginRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

//    @Autowired
//    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/name")
    public ResponseEntity<String> welcome() {
        return ResponseEntity.ok("Welcome Authuntication");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest loginRequest) {
        try {
            System.out.println("Login Processing");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

            HttpSession session = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest().getSession(true);

            System.out.println("Session ID after authentication: " + session.getId());

            String role = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                    .findFirst().orElseThrow(() -> new RuntimeException("No role for this user"));

            Map<String, String> response = new HashMap<>();
            response.put("message", "Login successful");
            Optional<UserEntity> user=userRepository.findByEmail(authentication.getName());
            UserEntity userEntity=user.get();
            response.put("userId", String.valueOf(userEntity.getUserId()));
            response.put("role", role);
            response.put("details",userEntity.toString());

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("message", "Login failed. Invalid username or password."));
        }
    }

//    @PostMapping("/register")
//    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto){
//        try{
//            User user=new User();
//            user.setUsername(userDto.getUsername());
//            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
//
//            Role role=rolesRepo.getReferenceById((long)1);
//            user.setRole(role);
//
//            UserProfile userProfile = new UserProfile();
//            userProfile.setFullName(userDto.getFullName());
//            userProfile.setPhoneNumber(userDto.getPhoneNumber());
//            userProfile.setAddress(userDto.getAddress());
//            userProfile.setEmail(userDto.getEmail());
//
//            user.setUserProfile(userProfile);
//
//            userService.saveUser(user);
//            return new ResponseEntity<>("User register succussfully", HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Registration failed"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

}
