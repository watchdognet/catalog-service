package com.watchdognet.catalog_service.infrastructure.adapter.driven.messaging.outbox

import com.fasterxml.jackson.databind.ObjectMapper
import com.watchdognet.catalog_service.domain.model.events.DomainEvent
import com.watchdognet.catalog_service.domain.model.events.DomainEventPublisher
import com.watchdognet.catalog_service.domain.model.events.ProductCreatedEvent
import com.watchdognet.catalog_service.domain.model.events.ProductStateChangedEvent
import com.watchdognet.catalog_service.domain.model.events.ProductStatusChangedEvent
import com.watchdognet.catalog_service.domain.model.events.ProductStockUpdatedEvent
import com.watchdognet.catalog_service.domain.model.events.ProductUpdatedEvent
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime
import java.util.UUID

@Component
class OutboxEventPublisher(
  private val outboxRepository: OutboxRepository,
  private val objectMapper: ObjectMapper
) : DomainEventPublisher {

  @Transactional(propagation = Propagation.MANDATORY)
  override fun publish(event: DomainEvent) {
    val eventType = event.javaClass.simpleName
    val payload = objectMapper.writeValueAsString(event)

    val outboxMessage = OutboxMessage(
      id = UUID.randomUUID().toString(),
      aggregateType = getAggregateType(event),
      aggregateId = getAggregateId(event),
      eventType = eventType,
      routingKey = determineRoutingKey(event),
      payload = payload,
      createdAt = OffsetDateTime.now()
    )

    outboxRepository.save(outboxMessage)
  }

  private fun getAggregateType(event: DomainEvent): String {
    return "Product"
  }

  private fun getAggregateId(event: DomainEvent): String {
    return when (event) {
      is ProductCreatedEvent -> event.productId
      is ProductUpdatedEvent -> event.productId
      is ProductStockUpdatedEvent -> event.productId
      is ProductStatusChangedEvent -> event.productId
      is ProductStateChangedEvent -> event.productId
      else -> throw IllegalArgumentException("Event type not supported: ${event.javaClass.simpleName}")
    }
  }

  private fun determineRoutingKey(event: DomainEvent): String {
    return when (event) {
      is ProductCreatedEvent -> "product.created"
      is ProductUpdatedEvent -> "product.updated"
      is ProductStockUpdatedEvent -> "product.stock.updated"
      is ProductStatusChangedEvent -> "product.status.changed"
      is ProductStateChangedEvent -> "product.state"
      else -> throw IllegalArgumentException("Event type not supported: ${event.javaClass.simpleName}")
    }
  }
}
