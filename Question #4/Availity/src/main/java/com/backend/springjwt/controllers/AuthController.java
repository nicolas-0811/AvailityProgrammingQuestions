package com.backend.springjwt.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.backend.springjwt.models.*;
import com.backend.springjwt.payload.request.*;
import com.backend.springjwt.payload.response.*;
import com.backend.springjwt.repository.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

  @Autowired
  UserRepository userRepository;

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByNpi(signUpRequest.getNpi())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: NPI number has is already been used"));
    }
    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("Error: Email is already in use"));
    }
    // Create new user's account
    User user = new User(signUpRequest.getFirstName(),
            signUpRequest.getLastName(),
            signUpRequest.getNpi(),
            signUpRequest.getAddress(),
            signUpRequest.getPhone(),
            signUpRequest.getEmail()
            );
    userRepository.save(user);
    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}