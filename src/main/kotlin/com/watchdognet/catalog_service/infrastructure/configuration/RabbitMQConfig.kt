package com.watchdognet.catalog_service.infrastructure.configuration

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.core.QueueBuilder
import org.springframework.amqp.core.TopicExchange
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.beans.factory.annotation.Qualifier

@Configuration
class RabbitMQConfig {

  @Bean
  fun productExchange(): TopicExchange {
    return TopicExchange("product-exchange", true, false)
  }

  @Bean
  fun deadLetterProductExchange(): DirectExchange {
    return DirectExchange("dead-letter-product-exchange", true, false)
  }

  @Bean
  fun productCreatedQueue(): Queue {
    return QueueBuilder.durable("product-created-queue")
      .withArgument("x-dead-letter-exchange", "dead-letter-product-exchange")
      .withArgument("x-dead-letter-routing-key", "dead.product.created")
      .build()
  }

  @Bean
  fun productUpdatedQueue(): Queue {
    return QueueBuilder.durable("product-updated-queue")
      .withArgument("x-dead-letter-exchange", "dead-letter-product-exchange")
      .withArgument("x-dead-letter-routing-key", "dead.product.updated")
      .build()
  }

  @Bean
  fun productStockUpdatedQueue(): Queue {
    return QueueBuilder.durable("product-stock-updated-queue")
      .withArgument("x-dead-letter-exchange", "dead-letter-product-exchange")
      .withArgument("x-dead-letter-routing-key", "dead.product.stock.updated")
      .build()
  }

  @Bean
  fun productStatusChangedQueue(): Queue {
    return QueueBuilder.durable("product-status-changed-queue")
      .withArgument("x-dead-letter-exchange", "dead-letter-product-exchange")
      .withArgument("x-dead-letter-routing-key", "dead.product.status.changed")
      .build()
  }

  @Bean
  fun productStateQueue(): Queue {
    return QueueBuilder.durable("product-state-queue")
      .withArgument("x-dead-letter-exchange", "dead-letter-product-exchange")
      .withArgument("x-dead-letter-routing-key", "dead.product.state")
      .build()
  }

  @Bean
  fun deadLetterProductQueue(): Queue {
    return QueueBuilder.durable("dead-letter-product-queue").build()
  }

  @Bean
  fun productCreatedBinding(productCreatedQueue: Queue, catalogExchange: TopicExchange): Binding {
    return BindingBuilder
      .bind(productCreatedQueue)
      .to(catalogExchange)
      .with("product.created")
  }

  @Bean
  fun productUpdatedBinding(productUpdatedQueue: Queue, catalogExchange: TopicExchange): Binding {
    return BindingBuilder
      .bind(productUpdatedQueue)
      .to(catalogExchange)
      .with("product.updated")
  }

  @Bean
  fun productStockUpdatedBinding(productStockUpdatedQueue: Queue, catalogExchange: TopicExchange): Binding {
    return BindingBuilder
      .bind(productStockUpdatedQueue)
      .to(catalogExchange)
      .with("product.stock.updated")
  }

  @Bean
  fun productStatusChangedBinding(productStatusChangedQueue: Queue, catalogExchange: TopicExchange): Binding {
    return BindingBuilder
      .bind(productStatusChangedQueue)
      .to(catalogExchange)
      .with("product.status.changed")
  }

  @Bean
  fun productStateBinding(productStateQueue: Queue, catalogExchange: TopicExchange): Binding {
    return BindingBuilder
      .bind(productStateQueue)
      .to(catalogExchange)
      .with("product.state")
  }

  @Bean
  fun deadProductCreatedBinding(
    @Qualifier("deadLetterProductQueue") deadLetterQueue: Queue,
    deadLetterExchange: DirectExchange
  ): Binding {
    return BindingBuilder
      .bind(deadLetterQueue)
      .to(deadLetterExchange)
      .with("dead.product.created")
  }

  @Bean
  fun deadProductUpdatedBinding(
    @Qualifier("deadLetterProductQueue") deadLetterQueue: Queue,
    deadLetterExchange: DirectExchange
  ): Binding {
    return BindingBuilder
      .bind(deadLetterQueue)
      .to(deadLetterExchange)
      .with("dead.product.updated")
  }

  @Bean
  fun deadProductStockUpdatedBinding(
    @Qualifier("deadLetterProductQueue") deadLetterQueue: Queue,
    deadLetterExchange: DirectExchange
  ): Binding {
    return BindingBuilder
      .bind(deadLetterQueue)
      .to(deadLetterExchange)
      .with("dead.product.stock.updated")
  }

  @Bean
  fun deadProductStatusChangedBinding(
    @Qualifier("deadLetterProductQueue") deadLetterQueue: Queue,
    deadLetterExchange: DirectExchange
  ): Binding {
    return BindingBuilder
      .bind(deadLetterQueue)
      .to(deadLetterExchange)
      .with("dead.product.status.changed")
  }

  @Bean
  fun deadProductStateBinding(
    @Qualifier("deadLetterProductQueue") deadLetterQueue: Queue,
    deadLetterExchange: DirectExchange
  ): Binding {
    return BindingBuilder
      .bind(deadLetterQueue)
      .to(deadLetterExchange)
      .with("dead.product.state")
  }

  @Bean
  fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
    val rabbitTemplate = RabbitTemplate(connectionFactory)
    rabbitTemplate.messageConverter = Jackson2JsonMessageConverter()
    return rabbitTemplate
  }

  @Bean
  fun rabbitAdmin(connectionFactory: ConnectionFactory): RabbitAdmin {
    return RabbitAdmin(connectionFactory)
  }
}