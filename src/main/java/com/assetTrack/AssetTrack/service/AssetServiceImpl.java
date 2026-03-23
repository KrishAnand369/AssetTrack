package com.assetTrack.AssetTrack.service;

import com.assetTrack.AssetTrack.dto.AssetDto;
import com.assetTrack.AssetTrack.dto.AssetResponse;
import com.assetTrack.AssetTrack.entity.Asset;
import com.assetTrack.AssetTrack.repository.AssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    @Override
    public AssetResponse createAsset(AssetDto assetDto) {
        Asset asset = new Asset();
        asset.setName(assetDto.name());
        asset.setDescription(assetDto.description());
        asset.setSerialNumber(assetDto.serialNumber());
        asset.setCreatedAt(LocalDateTime.now());
        asset.setUpdatedAt(LocalDateTime.now());
        
        Asset savedAsset = assetRepository.save(asset);
        return AssetResponse.fromEntity(savedAsset);
    }

    @Override
    public List<AssetResponse> getAllAssets() {
        return assetRepository.findAll().stream()
                .map(AssetResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AssetResponse> getAssetById(Long id) {
        return assetRepository.findById(id).map(AssetResponse::fromEntity);
    }

    @Override
    public AssetResponse updateAsset(Long id, AssetDto assetDto) {
        Optional<Asset> existingAssetOpt = assetRepository.findById(id);
        if (existingAssetOpt.isPresent()) {
            Asset existingAsset = existingAssetOpt.get();
            if (assetDto.name() != null) {
                existingAsset.setName(assetDto.name());
            }
            if (assetDto.description() != null) {
                existingAsset.setDescription(assetDto.description());
            }
            if (assetDto.serialNumber() != null) {
                existingAsset.setSerialNumber(assetDto.serialNumber());
            }
            existingAsset.setUpdatedAt(LocalDateTime.now());
            
            Asset updatedAsset = assetRepository.save(existingAsset);
            return AssetResponse.fromEntity(updatedAsset);
        }
        throw new RuntimeException("Asset not found with id " + id);
    }

    @Override
    public void deleteAsset(Long id) {
        assetRepository.deleteById(id);
    }
}
