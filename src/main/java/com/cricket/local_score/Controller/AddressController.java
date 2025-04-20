package com.cricket.local_score.Controller;

import com.cricket.local_score.response.ApiResponse;
import com.cricket.local_score.Service.addressEntity.IaddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/address")
public class AddressController {

    @Autowired
    private IaddressService service;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getAddress2(@PathVariable Integer id) {
        try {
            String address = service.getAddress2(id);
            return new ResponseEntity<>(new ApiResponse("Address2 fetched", address), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error fetching address2: " + e.getMessage(), null));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllAddress2() {
        try {
            List<Map<String, Object>> list = service.getAllAddress2();
            return new ResponseEntity<>(new ApiResponse("All address2 fetched", list), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Error fetching all address2: " + e.getMessage(), null));
        }
    }
}
