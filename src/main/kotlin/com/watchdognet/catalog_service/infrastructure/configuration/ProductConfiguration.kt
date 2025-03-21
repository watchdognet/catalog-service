package com.watchdognet.catalog_service.infrastructure.configuration

import com.watchdognet.catalog_service.application.product.create.CreateProductUseCase
import com.watchdognet.catalog_service.application.product.update.UpdateProductStockUseCase
import com.watchdognet.catalog_service.domain.model.events.DomainEventPublisher
import com.watchdognet.catalog_service.domain.model.repository.ProductRepository
import com.watchdognet.catalog_service.infrastructure.repository.product.ProductJpaRepository
import com.watchdognet.catalog_service.infrastructure.repository.product.ProductRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ProductConfiguration {

  @Bean
  fun createProductUseCase(
    productRepository: ProductRepository,
    eventPublisher: DomainEventPublisher
  ): CreateProductUseCase {
    return CreateProductUseCase(productRepository, eventPublisher)
  }

  @Bean
  fun productRepository(
    jpaProductRepository: ProductJpaRepository
  ): ProductRepository {
    return ProductRepositoryImpl(jpaProductRepository)
  }

  @Bean
  fun updateProductStock(
    productRepository: ProductRepository,
    eventPublisher: DomainEventPublisher
  ) = UpdateProductStockUseCase(
    productRepository = productRepository,
    eventPublisher = eventPublisher
  )
}
