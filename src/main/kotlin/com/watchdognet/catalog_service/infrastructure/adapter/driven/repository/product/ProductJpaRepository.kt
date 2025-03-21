package com.watchdognet.catalog_service.infrastructure.adapter.driven.repository.product

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductJpaRepository : JpaRepository<ProductEntity, String> {
  fun findByNameContainingIgnoreCase(name: String): List<ProductEntity>
  fun countByActive(active: Boolean): Long
}
