package com.watchdognet.catalog_service.infrastructure.repository.product

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.OffsetDateTime

@Entity
@Table(name = "products")
data class ProductEntity(
  @Id
  val id: String,

  @Column(name = "product_name", nullable = false)
  val name: String,

  @Column(nullable = false)
  val description: String,

  @Column(nullable = false)
  val price: Double,

  @Column(nullable = false)
  val stock: Int,

  @Column(nullable = false)
  val active: Boolean,

  @Column(name = "created_at", nullable = false)
  val createdAt: OffsetDateTime,

  @Column(name = "modified_at")
  val modifiedAt: OffsetDateTime?
)
