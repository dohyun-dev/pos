package com.dohyundev.pos.core.catalog.product

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProductQueryServiceV1(
    private val productRepository: ProductRepository
) {
    fun getProducts(): List<ProductResponse.Summary> {
        return productRepository.findAll()
            .map { ProductResponse.Summary.from(it) }
    }

    fun getProduct(productId: String): ProductResponse.Detail {
        val product = productRepository.findByIdOrNull(productId)
            ?: throw EntityNotFoundException("제품을 찾을 수 없습니다. ID: $productId")
        
        return ProductResponse.Detail.from(product)
    }
}
