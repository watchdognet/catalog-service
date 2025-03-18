package com.watchdognet.catalog_service.application.product.update

data class UpdateProductCommand(
  val id: String,
  val name: String,
  val description: String,
  val price: Double,
  val stock: Int,
  val active: Boolean
)
