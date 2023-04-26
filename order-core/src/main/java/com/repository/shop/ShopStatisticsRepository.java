package com.repository.shop;

import com.entity.shop.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopStatisticsRepository extends JpaRepository<Shop,Long> {
}
