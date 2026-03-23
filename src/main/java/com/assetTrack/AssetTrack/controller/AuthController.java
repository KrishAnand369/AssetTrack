package com.assetTrack.AssetTrack.controller;

import com.assetTrack.AssetTrack.dto.LoginRequest;
import com.assetTrack.AssetTrack.dto.LoginResponse;
import com.assetTrack.AssetTrack.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authentication.getName());

        return ResponseEntity.ok(new LoginResponse(jwt));
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        // Since JWT is stateless, actual invalidation happens client-side by deleting the token.
        // We can just return a success message here or implement a blacklist.
        return ResponseEntity.ok(Map.of("message", "User logged out successfully. Please remove token from client."));
    }
}
