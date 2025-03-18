package com.watchdognet.catalog_service.application.product.update

import com.watchdognet.catalog_service.domain.model.Product
import com.watchdognet.catalog_service.domain.model.events.DomainEventPublisher
import com.watchdognet.catalog_service.domain.model.exception.ProductNotFoundException
import com.watchdognet.catalog_service.domain.model.repository.ProductRepository

class UpdateProductUseCase(
  private val productRepository: ProductRepository,
  private val eventPublisher: DomainEventPublisher
) {

  fun execute(command: UpdateProductCommand): Product {
    val product = getProductById(command.id)

    product.updateDetails(
      name = command.name,
      description = command.description,
      price = command.price,
      stock = command.stock
    )

    if (product.isActive() && !command.active) {
      product.deactivate()
    } else {
      if (!product.isActive() && command.active) {
        product.activate()
      }
    }

    val updatedProduct = productRepository.save(product)

    product.getEvents().forEach { eventPublisher.publish(it) }

    return updatedProduct
  }

  private fun getProductById(id: String): Product {
    return productRepository.findById(id)
      .orElseThrow { ProductNotFoundException("Product with ID $id not found") }
  }
}