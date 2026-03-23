package com.assetTrack.AssetTrack.repository;

import com.assetTrack.AssetTrack.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
}
