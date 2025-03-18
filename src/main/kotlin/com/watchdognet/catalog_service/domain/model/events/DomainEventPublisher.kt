package com.watchdognet.catalog_service.domain.model.events

interface DomainEventPublisher {
  fun publish(event: DomainEvent)
}
