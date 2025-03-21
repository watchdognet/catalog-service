package com.watchdognet.catalog_service.application.product.create

import java.math.BigDecimal

data class CreateProductCommand(
  val name: String,
  val description: String,
  val price: BigDecimal,
  val stock: Int
)
