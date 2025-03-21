package com.watchdognet.catalog_service.domain.model.events

import java.math.BigDecimal
import java.time.OffsetDateTime

data class ProductStateChangedEvent(
  val productId: String,
  val name: String,
  val description: String,
  val price: BigDecimal,
  val stock: Int,
  val active: Boolean,
  val createdAt: OffsetDateTime,
  val modifiedAt: OffsetDateTime?
) : DomainEvent()
