package com.watchdognet.catalog_service.domain.model

data class Price private constructor(val value: Double) {
  companion object {
    fun of(value: Double): Price {
      require(value > 0) { "Price must be greater than zero" }
      return Price(value)
    }
  }
}
