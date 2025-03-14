package com.watchdognet.catalog_service.domain.model

data class Description private constructor(val value: String) {
  companion object {
    fun of(value: String): Description {
      require(value.isNotBlank()) { "Description cannot be blank" }
      return Description(value)
    }
  }
}
