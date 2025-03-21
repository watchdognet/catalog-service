package com.watchdognet.catalog_service.application.product.update

import java.math.BigDecimal

data class UpdateProductCommand(
  val id: String,
  val name: String,
  val description: String,
  val price: BigDecimal,
  val stock: Int,
  val active: Boolean
)
