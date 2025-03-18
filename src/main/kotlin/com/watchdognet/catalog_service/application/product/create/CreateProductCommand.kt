package com.watchdognet.catalog_service.application.product.create

data class CreateProductCommand(
  val name: String,
  val description: String,
  val price: Double,
  val stock: Int
)
