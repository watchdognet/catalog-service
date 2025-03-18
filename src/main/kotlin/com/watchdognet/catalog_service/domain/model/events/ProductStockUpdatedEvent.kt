package com.watchdognet.catalog_service.domain.model.events

import java.time.OffsetDateTime

data class ProductStockUpdatedEvent(
  val productId: String,
  val stock: Int,
  val modifiedAt: OffsetDateTime
) : DomainEvent()
