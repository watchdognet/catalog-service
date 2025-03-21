package com.watchdognet.catalog_service.application.product.update

import com.watchdognet.catalog_service.domain.model.Product
import com.watchdognet.catalog_service.domain.model.events.DomainEventPublisher
import com.watchdognet.catalog_service.domain.model.exception.ProductNotFoundException
import com.watchdognet.catalog_service.domain.model.repository.ProductRepository

class UpdateProductStockUseCase(
  private val productRepository: ProductRepository,
  private val eventPublisher: DomainEventPublisher
) {
  fun execute(command: UpdateProductStockCommand): Product {
    val product = getProductById(command.productId)

    product.updateStock(command.newStock)

    val updatedProduct = productRepository.save(product)

    product.getEvents().forEach { eventPublisher.publish(it) }

    return updatedProduct
  }

  private fun getProductById(id: String): Product {
    return productRepository.findById(id)
      .orElseThrow { ProductNotFoundException("Product with ID $id not found") }
  }
}
