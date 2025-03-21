package com.watchdognet.catalog_service.infrastructure.messaging.outbox

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime

@Service
class OutboxProcessorService(
  private val outboxRepository: OutboxRepository,
  private val rabbitTemplate: RabbitTemplate
) {

  @Scheduled(fixedRate = 5000)
  @Transactional
  fun processOutboxMessages() {
    val unprocessedMessages = outboxRepository.findUnprocessedMessages()

    if (unprocessedMessages.isNotEmpty()) {
      logger.info("Processing ${unprocessedMessages.size} outbox messages")

      unprocessedMessages.forEach { message ->
        try {
          rabbitTemplate.convertAndSend(
            EXCHANGE_NAME,
            message.routingKey,
            message.payload
          )

          val processedMessage = message.copy(processedAt = OffsetDateTime.now())
          outboxRepository.save(processedMessage)

          logger.debug("Processed message: ${message.id}")
        } catch (e: Exception) {
          logger.error("Error processing message ${message.id}: ${e.message}", e)
        }
      }
    }
  }

  companion object {
    private const val EXCHANGE_NAME = "product-exchange"

    private val logger = LoggerFactory.getLogger(this::class.java)
  }
}
