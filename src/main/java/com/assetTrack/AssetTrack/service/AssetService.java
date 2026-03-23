package com.assetTrack.AssetTrack.service;

import com.assetTrack.AssetTrack.dto.AssetDto;

import com.assetTrack.AssetTrack.dto.AssetResponse;

import java.util.List;
import java.util.Optional;

public interface AssetService {

    AssetResponse createAsset(AssetDto assetDto);

    List<AssetResponse> getAllAssets();

    Optional<AssetResponse> getAssetById(Long id);

    AssetResponse updateAsset(Long id, AssetDto assetDto);

    void deleteAsset(Long id);
}
