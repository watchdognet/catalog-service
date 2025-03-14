package com.watchdognet.catalog_service.domain.model

import java.util.UUID

data class ProductId private constructor(val value: String) {
  companion object {
    fun new(): ProductId = ProductId(UUID.randomUUID().toString())
    fun of(value: String): ProductId = ProductId(UUID.fromString(value).toString())
  }
}
