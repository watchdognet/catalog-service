package com.watchdognet.catalog_service.domain.model

data class Name private constructor(val value: String) {
  companion object {
    fun of(value: String): Name {
      require(value.isNotBlank()) { "Product name cannot be blank" }
      return Name(value)
    }
  }
}
