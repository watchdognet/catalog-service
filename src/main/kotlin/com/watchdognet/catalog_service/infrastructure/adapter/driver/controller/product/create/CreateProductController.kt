package com.watchdognet.catalog_service.infrastructure.adapter.driver.controller.product.create

import com.watchdognet.catalog_service.application.product.create.CreateProductCommand
import com.watchdognet.catalog_service.application.product.create.CreateProductUseCase
import com.watchdognet.catalog_service.infrastructure.adapter.driver.controller.product.ProductResponse
import com.watchdognet.catalog_service.infrastructure.adapter.driver.controller.product.ProductMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CreateProductController(private val createProductUseCase: CreateProductUseCase) {

  @PostMapping("/products")
  @Transactional
  fun createProduct(@RequestBody request: CreateProductRequest): ResponseEntity<ProductResponse> {
    val command = CreateProductCommand(
      name = request.name,
      description = request.description,
      price = request.price,
      stock = request.stock
    )

    val product = createProductUseCase.execute(command)
    val response = ProductMapper.toResponse(product)

    return ResponseEntity(response, HttpStatus.CREATED)
  }


}
