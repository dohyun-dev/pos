package com.dohyundev.pos.core.catalog.product

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductCommandServiceV1(
    private val productRepository: ProductRepository
) {
    @Transactional
    fun createProduct(
        command: ProductCommand.CreateProduct
    ) {
    }

    @Transactional
    fun updateProduct(command: ProductCommand.UpdateProduct) {
        TODO("Not yet implemented")
    }

    @Transactional
    fun deleteProduct(productId: String) {
        val product = productRepository.findByIdOrNull(productId) ?: throw EntityNotFoundException()
        productRepository.delete(product)
    }


    // TODO Category 삭제시 product가 있는지 검증 있으면 exception
}
