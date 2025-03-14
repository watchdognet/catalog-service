package com.watchdognet.catalog_service.domain.model

import java.time.OffsetDateTime

class Product private constructor(
  val id: ProductId,
  var name: Name,
  var description: Description,
  var price: Price,
  var stock: Stock,
  var active: Boolean = true,
  val createdAt: ProductTimestamp = ProductTimestamp.now(),
  var modifiedAt: ProductTimestamp? = null
) {
  fun getId(): String = id.value
  fun getName(): String = name.value
  fun getDescription(): String = description.value
  fun getPrice(): Double = price.value
  fun getStock(): Int = stock.value
  fun isActive(): Boolean = active
  fun getCreatedAt(): OffsetDateTime = createdAt.value
  fun getModifiedAt(): OffsetDateTime? = modifiedAt?.value

  companion object {
    fun create(name: String, description: String, price: Double, stock: Int): Product {
      return Product(
        id = ProductId.new(),
        name = Name.of(name),
        description = Description.of(description),
        price = Price.of(price),
        stock = Stock.of(stock),
      )
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
