package com.watchdognet.catalog_service.application.product.get

import com.watchdognet.catalog_service.domain.model.Product
import com.watchdognet.catalog_service.domain.model.repository.ProductRepository
import java.util.*

class GetProductByIdUseCase(private val productRepository: ProductRepository) {
  fun execute(id: String): Optional<Product> {
    return productRepository.findById(id)
  }
}
