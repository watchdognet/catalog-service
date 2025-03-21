package com.watchdognet.catalog_service.infrastructure.adapter.driven.repository.product

import com.watchdognet.catalog_service.domain.model.Product
import com.watchdognet.catalog_service.domain.model.repository.ProductRepository
import java.util.Optional

class ProductRepositoryImpl(
  private val jpaProductRepository: ProductJpaRepository
) : ProductRepository {

  override fun save(product: Product): Product {
    val entity = ProductMapper.toEntity(product)
    val savedEntity = jpaProductRepository.save(entity)
    return ProductMapper.toDomain(savedEntity)
  }

  override fun findById(id: String): Optional<Product> {
    return jpaProductRepository.findById(id)
      .map { ProductMapper.toDomain(it) }
  }

  override fun findAll(): List<Product> {
    return jpaProductRepository.findAll()
      .map { ProductMapper.toDomain(it) }
  }

  override fun findByNameContaining(name: String): List<Product> {
    return jpaProductRepository.findByNameContainingIgnoreCase(name)
      .map { ProductMapper.toDomain(it) }
  }

  override fun delete(id: String) {
    jpaProductRepository.deleteById(id)
  }
}