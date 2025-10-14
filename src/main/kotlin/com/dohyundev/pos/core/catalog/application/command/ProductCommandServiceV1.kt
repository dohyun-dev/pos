package com.dohyundev.pos.core.catalog.application.command

import com.dohyundev.pos.core.catalog.domain.entity.Product
import com.dohyundev.pos.core.catalog.domain.repository.ProductRepository
import com.dohyundev.pos.core.catalog.dto.ProductCommand
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class ProductCommandServiceV1(
    private val productRepository: ProductRepository
) {

    @Transactional
    fun createProduct(command: ProductCommand.CreateProduct): Product {
        val product = command.toEntity()
        if (productRepository.existsByCode(product.code)) {
            throw EntityNotFoundException("이미 존재하는 상품 코드입니다: ${product.code}")
        }
        return productRepository.save(product)
    }

    @Transactional
    fun updateProduct(id: String, command: ProductCommand.UpdateProduct): Product {
        val product = productRepository.findById(id)
            .orElseThrow { IllegalArgumentException("상품을 찾을 수 없습니다: $id") }

        product.update(
            code = command.code!!,
            barcode = command.barcode,
            uom = command.uom,
            name = command.name!!,
            description = command.description,
            basePrice = command.basePrice ?: BigDecimal.ZERO
        )

        return productRepository.save(product)
    }

    @Transactional
    fun deleteProduct(id: String) {
        val product = productRepository.findById(id)
            .orElseThrow { IllegalArgumentException("상품을 찾을 수 없습니다: $id") }
        productRepository.delete(product)
    }
}
