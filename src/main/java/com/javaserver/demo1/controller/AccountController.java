package com.javaserver.demo1.controller;

import com.javaserver.demo1.model.Account;
import com.javaserver.demo1.repository.AccountRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.javaserver.demo1.JwtResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import io.jsonwebtoken.security.Keys;

import java.util.Base64;
import java.util.Date;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173/") // Add this line to allow requests from your Vue application
public class AccountController {

    private final AccountRepository accountRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @PostMapping("/register")
    public Account postRegisterAccount(@RequestBody Account registerAccount) {
        String encodedPassword = passwordEncoder.encode(registerAccount.getPassword());
        registerAccount.setPassword(encodedPassword);
        return accountRepository.save(registerAccount);
    }

    private String generateJwtToken(String username) {
        byte[] keyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();
        String base64Key = Base64.getEncoder().encodeToString(keyBytes);
//        String secretKey = "webProjectKey"; // Replace with your own secret key

        return Jwts.builder().setSubject(username).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 864000000)) // Set token expiration time (e.g., 10 days)
                .signWith(SignatureAlgorithm.HS512, base64Key).compact();
    }

    @PostMapping("/login")
    public ResponseEntity<?> postLoginAccount(@RequestBody Account loginAccount) {
        try {
            // Retrieve the user account from the database
            Account userAccount = accountRepository.findByUsername(loginAccount.getUsername());

            if (userAccount == null) {
                // User account not found, return UNAUTHORIZED response
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            // Compare the encoded password stored in the database with the user's input
            if (passwordEncoder.matches(loginAccount.getPassword(), userAccount.getPassword())) {
                // Passwords match, proceed with authentication

                // Authenticate the user
                Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginAccount.getUsername(), loginAccount.getPassword()));

                // Generate JWT token for the authenticated user
                UserDetails userDetails = userDetailsService.loadUserByUsername(loginAccount.getUsername());
                String token = generateJwtToken(userDetails.getUsername());

                // Return the JWT token as the response
                return ResponseEntity.ok(new JwtResponse(token));
            } else {
                // Passwords do not match, return UNAUTHORIZED response
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (AuthenticationException e) {
            // Handle authentication failure
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
