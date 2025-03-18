package com.watchdognet.catalog_service.application.product.get

import com.watchdognet.catalog_service.domain.model.Product
import com.watchdognet.catalog_service.domain.model.repository.ProductRepository

class GetAllProductsUseCase(private val productRepository: ProductRepository) {
  fun execute(): List<Product> {
    return productRepository.findAll()
  }
}
