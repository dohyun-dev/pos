package com.dohyundev.pos.core.catalog.product.query

import com.dohyundev.pos.core.catalog.product.command.domain.ProductRepository
import com.dohyundev.pos.core.catalog.product.dto.ProductDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductQueryServiceV1(
    private val productRepository: ProductRepository,
) {
    fun getAllProducts(): List<ProductDto> {
        return productRepository.findAll()
            .map { ProductDto.from(it) }
    }
}