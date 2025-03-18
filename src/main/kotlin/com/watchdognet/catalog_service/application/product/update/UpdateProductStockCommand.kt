package com.watchdognet.catalog_service.application.product.update

data class UpdateProductStockCommand(
  val productId: String,
  val newStock: Int
)