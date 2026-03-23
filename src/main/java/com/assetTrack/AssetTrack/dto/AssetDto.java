package com.assetTrack.AssetTrack.dto;

import lombok.Data;

public record AssetDto(
    String name,
    String description,
    String serialNumber
) {}
