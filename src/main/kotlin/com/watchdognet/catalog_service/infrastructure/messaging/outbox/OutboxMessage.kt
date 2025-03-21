package com.watchdognet.catalog_service.infrastructure.messaging.outbox

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.OffsetDateTime

@Entity
@Table(name = "outbox_messages")
data class OutboxMessage(
  @Id
  val id: String,

  @Column(name = "aggregate_type", nullable = false)
  val aggregateType: String,

  @Column(name = "aggregate_id", nullable = false)
  val aggregateId: String,

  @Column(name = "event_type", nullable = false)
  val eventType: String,

  @Column(name = "routing_key", nullable = false)
  val routingKey: String,

  @Column(nullable = false, columnDefinition = "TEXT")
  val payload: String,

  @Column(name = "created_at", nullable = false)
  val createdAt: OffsetDateTime,

  @Column(name = "processed_at")
  val processedAt: OffsetDateTime? = null
)
