package com.watchdognet.catalog_service.domain.model

import com.watchdognet.catalog_service.domain.model.events.DomainEvent
import com.watchdognet.catalog_service.domain.model.events.ProductCreatedEvent
import java.time.OffsetDateTime

class Product private constructor(
  val id: ProductId,
  var name: Name,
  var description: Description,
  var price: Price,
  var stock: Stock,
  var active: Boolean = true,
  val createdAt: ProductTimestamp = ProductTimestamp.now(),
  var modifiedAt: ProductTimestamp? = null,
  private val events: MutableList<DomainEvent> = mutableListOf()
) {
  fun getId(): String = id.value
  fun getName(): String = name.value
  fun getDescription(): String = description.value
  fun getPrice(): Double = price.value
  fun getStock(): Int = stock.value
  fun isActive(): Boolean = active
  fun getCreatedAt(): OffsetDateTime = createdAt.value
  fun getModifiedAt(): OffsetDateTime? = modifiedAt?.value

  private fun registerEvent(event: DomainEvent) {
    events.add(event)
  }

  companion object {
    fun create(name: String, description: String, price: Double, stock: Int): Product {
      val product = Product(
        id = ProductId.new(),
        name = Name.of(name),
        description = Description.of(description),
        price = Price.of(price),
        stock = Stock.of(stock),
      )

      product.registerEvent(
        ProductCreatedEvent(
          productId =  product.getId(),
          name = product.getName(),
          description = product.getDescription(),
          price = product.getPrice(),
          stock = product.getStock(),
          active = product.isActive(),
          createdAt = product.createdAt.value
        )
      )

      return product
    }

    fun fromPersistence(
      id: String,
      name: String,
      description: String,
      price: Double,
      stock: Int,
      active: Boolean,
      createdAt: ProductTimestamp,
      modifiedAt: ProductTimestamp?
    ): Product {
      return Product(
        id = ProductId.of(id),
        name = Name.of(name),
        description = Description.of(description),
        price = Price.of(price),
        stock = Stock.of(stock),
        active = active,
        createdAt = createdAt,
        modifiedAt = modifiedAt
      )
    }
  }
}
