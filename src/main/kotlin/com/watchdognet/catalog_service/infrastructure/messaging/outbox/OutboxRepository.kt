package com.watchdognet.catalog_service.infrastructure.messaging.outbox

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OutboxRepository : JpaRepository<OutboxMessage, String> {

  @Query("SELECT o FROM OutboxMessage o WHERE o.processedAt IS NULL ORDER BY o.createdAt ASC")
  fun findUnprocessedMessages(): List<OutboxMessage>

  fun countByProcessedAtIsNull(): Long
}