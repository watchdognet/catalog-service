package com.watchdognet.catalog_service.infrastructure.controller.product

import com.watchdognet.catalog_service.domain.model.Product

object ProductMapper {
  fun toResponse(product: Product): ProductResponse {
    return ProductResponse(
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
}