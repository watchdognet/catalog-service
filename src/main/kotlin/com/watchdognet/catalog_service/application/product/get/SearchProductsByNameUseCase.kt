package com.watchdognet.catalog_service.application.product.get

import com.watchdognet.catalog_service.domain.model.Product
import com.watchdognet.catalog_service.domain.model.repository.ProductRepository

class SearchProductsByNameUseCase(private val productRepository: ProductRepository) {
  fun execute(name: String): List<Product> {
    return productRepository.findByNameContaining(name)
  }
}
