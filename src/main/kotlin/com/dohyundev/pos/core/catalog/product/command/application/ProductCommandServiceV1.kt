package com.dohyundev.pos.core.catalog.product.command.application

import com.dohyundev.pos.core.catalog.product.command.domain.ProductRepository
import com.dohyundev.pos.core.catalog.product.command.exception.ProductEntityNotFoundException
import com.dohyundev.pos.core.catalog.product.dto.ProductDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductCommandServiceV1(
    private val productRepository: ProductRepository,
) {
    @Transactional
    fun createProduct(command: ProductCommand.UpsertProduct): ProductDto {

    }

    @Transactional
    fun updateProduct(productId: Long, command: ProductCommand.UpsertProduct): ProductDto {

    }

    @Transactional
    fun bulkUpdateProduct(commands: List<ProductCommand.UpsertProduct>): List<ProductDto> {
        return commands.map {
            updateProduct(
                productId = it.id!!,
                command = it
            )
        }
    }

    @Transactional
    fun deleteProduct(productId: Long) {
        val product = productRepository.findByIdOrNull(productId)
            ?: throw ProductEntityNotFoundException()
        productRepository.delete(product)
    }
}
