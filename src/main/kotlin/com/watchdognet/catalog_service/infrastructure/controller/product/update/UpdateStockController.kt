package com.watchdognet.catalog_service.infrastructure.controller.product.update

import com.watchdognet.catalog_service.application.product.update.UpdateProductStockCommand
import com.watchdognet.catalog_service.application.product.update.UpdateProductStockUseCase
import com.watchdognet.catalog_service.infrastructure.controller.product.ProductMapper
import com.watchdognet.catalog_service.infrastructure.controller.product.ProductResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UpdateStockController(
  private val updateProductStockUseCase: UpdateProductStockUseCase
) {
  @PatchMapping("/products/{productId}/stock/{stock}")
  @Transactional
  fun updateStock(
    @PathVariable productId: String,
    @PathVariable stock: Int,
  ): ResponseEntity<ProductResponse> {
    val command = UpdateProductStockCommand(
      productId = productId,
      newStock = stock
    )

    val product = updateProductStockUseCase.execute(command)
    val response = ProductMapper.toResponse(product)

    return ResponseEntity(response, HttpStatus.OK)
  }
}
