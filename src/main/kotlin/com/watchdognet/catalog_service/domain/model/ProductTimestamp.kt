package com.watchdognet.catalog_service.domain.model

import java.time.OffsetDateTime

data class ProductTimestamp private constructor(val value: OffsetDateTime) {
  companion object {
    fun now(): ProductTimestamp = ProductTimestamp(OffsetDateTime.now())
    fun of(value: OffsetDateTime): ProductTimestamp = ProductTimestamp(value)
  }
}
