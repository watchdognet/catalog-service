package com.watchdognet.catalog_service.infrastructure.adapter.driver.controller.product

import java.math.BigDecimal
import java.time.OffsetDateTime

data class ProductResponse(
  val id: String,
  val name: String,
  val description: String,
  val price: BigDecimal,
  val stock: Int,
  val active: Boolean,
  val createdAt: OffsetDateTime,
  val modifiedAt: OffsetDateTime?
)
