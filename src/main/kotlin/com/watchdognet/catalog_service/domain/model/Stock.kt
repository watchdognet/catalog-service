package com.watchdognet.catalog_service.domain.model

data class Stock private constructor(val value: Int) {
  companion object {
    fun of(value: Int): Stock {
      require(value >= 0) { "Stock cannot be negative" }
      return Stock(value)
    }
  }
}
