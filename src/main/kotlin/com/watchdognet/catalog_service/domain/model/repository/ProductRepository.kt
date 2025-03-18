package com.watchdognet.catalog_service.domain.model.repository

import com.watchdognet.catalog_service.domain.model.Product
import java.util.*

interface ProductRepository {
  fun save(product: Product): Product
  fun findById(id: String): Optional<Product>
  fun findAll(): List<Product>
  fun findByNameContaining(name: String): List<Product>
  fun update(product: Product): Product
  fun delete(id: String)
}
