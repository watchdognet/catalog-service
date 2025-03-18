package com.watchdognet.catalog_service.domain.model.events

import java.time.OffsetDateTime

data class ProductUpdatedEvent(
  val productId: String,
  val name: String,
  val description: String,
  val price: Double,
  val stock: Int,
  val active: Boolean,
  val modifiedAt: OffsetDateTime
) : DomainEvent()
