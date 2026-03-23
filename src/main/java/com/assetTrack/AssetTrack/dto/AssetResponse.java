package com.assetTrack.AssetTrack.dto;

import com.assetTrack.AssetTrack.entity.Asset;
//import java.time.LocalDateTime;

public record AssetResponse(
    Long id,
    String name,
    String description,
    String serialNumber
//    LocalDateTime createdAt,
//    LocalDateTime updatedAt
) {
    public static AssetResponse fromEntity(Asset asset) {
        return new AssetResponse(
            asset.getId(),
            asset.getName(),
            asset.getDescription(),
            asset.getSerialNumber()
//            asset.getCreatedAt(),
//            asset.getUpdatedAt()
        );
    }
}
