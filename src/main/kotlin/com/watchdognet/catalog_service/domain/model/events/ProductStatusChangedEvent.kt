package com.watchdognet.catalog_service.domain.model.events

import java.time.OffsetDateTime

data class ProductStatusChangedEvent(
  val productId: String,
  val active: Boolean,
  val modifiedAt: OffsetDateTime
) : DomainEvent()
