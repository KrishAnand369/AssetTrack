package com.assetTrack.AssetTrack.dto;

import lombok.Data;

public record LoginRequest(
    String username,
    String password
) {}
