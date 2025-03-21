package com.watchdognet.catalog_service.domain.model

import java.math.BigDecimal

data class Price private constructor(val value: BigDecimal) {
  companion object {
    fun of(value: BigDecimal): Price {
      require(value > BigDecimal.ZERO) { "Price must be greater than zero" }
      return Price(value)
    }
  }
}
