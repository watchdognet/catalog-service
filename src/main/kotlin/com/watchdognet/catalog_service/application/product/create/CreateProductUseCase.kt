package com.watchdognet.catalog_service.application.product.create

import com.watchdognet.catalog_service.domain.model.Product
import com.watchdognet.catalog_service.domain.model.events.DomainEventPublisher
import com.watchdognet.catalog_service.domain.model.repository.ProductRepository

class CreateProductUseCase(
  private val productRepository: ProductRepository,
  private val eventPublisher: DomainEventPublisher
) {
  fun execute(command: CreateProductCommand): Product {
    val product = Product.create(
      name = command.name,
      description = command.description,
      price = command.price,
      stock = command.stock
    )

    val savedProduct = productRepository.save(product)

    product.getEvents().forEach { eventPublisher.publish(it) }

    return savedProduct
  }
}
