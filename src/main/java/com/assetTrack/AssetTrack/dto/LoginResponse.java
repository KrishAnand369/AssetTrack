package com.assetTrack.AssetTrack.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

public record LoginResponse(
    String token,
    String type
) {
    public LoginResponse(String token) {
        this(token, "Bearer");
    }
}
