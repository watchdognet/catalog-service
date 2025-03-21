package com.watchdognet.catalog_service.infrastructure.adapter.driver.controller.product.create

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class CreateProductRequest(
  @JsonProperty("name") val name: String,
  @JsonProperty("description") val description: String,
  @JsonProperty("price") val price: BigDecimal,
  @JsonProperty("stock") val stock: Int
)
