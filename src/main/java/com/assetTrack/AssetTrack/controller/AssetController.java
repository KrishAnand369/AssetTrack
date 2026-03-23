package com.assetTrack.AssetTrack.controller;

import com.assetTrack.AssetTrack.dto.AssetDto;
import com.assetTrack.AssetTrack.dto.AssetResponse;
import com.assetTrack.AssetTrack.service.AssetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/assets")
@RequiredArgsConstructor
public class AssetController {

    private final AssetService assetService;

    @PostMapping
    public ResponseEntity<AssetResponse> createAsset(@RequestBody AssetDto assetDto) {
        AssetResponse createdAsset = assetService.createAsset(assetDto);
        return new ResponseEntity<>(createdAsset, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AssetResponse>> getAllAssets() {
        List<AssetResponse> assets = assetService.getAllAssets();
        return ResponseEntity.ok(assets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetResponse> getAssetById(@PathVariable Long id) {
        Optional<AssetResponse> asset = assetService.getAssetById(id);
        return asset.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateAsset(@PathVariable Long id, @RequestBody AssetDto assetDto) {
        try {
            AssetResponse updatedAsset = assetService.updateAsset(id, assetDto);
            return ResponseEntity.ok(updatedAsset);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAsset(@PathVariable Long id) {
        try {
            assetService.deleteAsset(id);
            return ResponseEntity.ok(Map.of("message", "Asset deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
