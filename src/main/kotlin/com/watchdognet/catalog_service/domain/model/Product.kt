package com.watchdognet.catalog_service.domain.model

import com.watchdognet.catalog_service.domain.model.events.DomainEvent
import com.watchdognet.catalog_service.domain.model.events.ProductCreatedEvent
import com.watchdognet.catalog_service.domain.model.events.ProductStateChangedEvent
import com.watchdognet.catalog_service.domain.model.events.ProductStatusChangedEvent
import com.watchdognet.catalog_service.domain.model.events.ProductStockUpdatedEvent
import com.watchdognet.catalog_service.domain.model.events.ProductUpdatedEvent
import java.math.BigDecimal

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
  fun getId() = id.value
  fun getName() = name.value
  fun getDescription() = description.value
  fun getPrice() = price.value
  fun getStock() = stock.value
  fun isActive() = active
  fun getCreatedAt() = createdAt.value
  fun getModifiedAt() = modifiedAt?.value

  private fun registerStateChangeEvent() {
    val stateEvent = ProductStateChangedEvent(
      productId = this.getId(),
      name = this.getName(),
      description = this.getDescription(),
      price = this.getPrice(),
      stock = this.getStock(),
      active = this.isActive(),
      createdAt = this.getCreatedAt(),
      modifiedAt = this.getModifiedAt()
    )

    registerEvent(stateEvent)
  }

  private fun registerEvent(event: DomainEvent) {
    events.add(event)
  }

  fun getEvents(): List<DomainEvent> {
    val eventsCopy = events.toList()
    events.clear()
    return eventsCopy
  }

  fun updateDetails(
    name: String,
    description: String,
    price: BigDecimal,
    stock: Int
  ) {
    this.name = Name.of(name)
    this.description = Description.of(description)
    this.price = Price.of(price)
    this.stock = Stock.of(stock)
    this.modifiedAt = ProductTimestamp.now()

    registerEvent(
      ProductUpdatedEvent(
        productId = getId(),
        name = getName(),
        description = getDescription(),
        price = getPrice(),
        stock = getStock(),
        active = isActive(),
        modifiedAt = getModifiedAt()!!
      )
    )
    registerStateChangeEvent()
  }

  fun updateStock(quantity: Int) {
    this.stock = Stock.of(quantity)
    this.modifiedAt = ProductTimestamp.now()

    registerEvent(
      ProductStockUpdatedEvent(
        productId = getId(),
        stock = getStock(),
        modifiedAt = getModifiedAt()!!
      )
    )
    registerStateChangeEvent()
  }

  fun activate() {
    if (!active) {
      this.active = true
      this.modifiedAt = ProductTimestamp.now()

      registerEvent(
        ProductStatusChangedEvent(
          productId = getId(),
          active = true,
          modifiedAt = getModifiedAt()!!
        )
      )
      registerStateChangeEvent()
    }
  }

  fun deactivate() {
    if (active) {
      this.active = false
      this.modifiedAt = ProductTimestamp.now()

      registerEvent(
        ProductStatusChangedEvent(
          productId = getId(),
          active = false,
          modifiedAt = getModifiedAt()!!
        )
      )
      registerStateChangeEvent()
    }
  }

  companion object {
    fun create(name: String, description: String, price: BigDecimal, stock: Int): Product {
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

      product.registerStateChangeEvent()

      return product
    }

    fun fromPersistence(
      id: String,
      name: String,
      description: String,
      price: BigDecimal,
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
