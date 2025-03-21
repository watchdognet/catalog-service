package com.watchdognet.catalog_service.infrastructure.adapter.driven.repository.product

import com.watchdognet.catalog_service.domain.model.Product
import com.watchdognet.catalog_service.domain.model.ProductTimestamp

object ProductMapper {
  fun toEntity(product: Product): ProductEntity {
    return ProductEntity(
      id = product.getId(),
      name = product.getName(),
      description = product.getDescription(),
      price = product.getPrice(),
      stock = product.getStock(),
      active = product.isActive(),
      createdAt = product.getCreatedAt(),
      modifiedAt = product.getModifiedAt()
    )
  }

  fun toDomain(entity: ProductEntity): Product {
    return Product.fromPersistence(
      id = entity.id,
      name = entity.name,
      description = entity.description,
      price = entity.price,
      stock = entity.stock,
      active = entity.active,
      createdAt = ProductTimestamp.of(entity.createdAt),
      modifiedAt = entity.modifiedAt?.let { ProductTimestamp.of(it) }
    )
  }
}