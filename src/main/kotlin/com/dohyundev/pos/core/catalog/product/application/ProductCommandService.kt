package com.dohyundev.pos.core.catalog.product.application

import com.dohyundev.pos.core.catalog.product.domain.Product
import com.dohyundev.pos.core.catalog.product.domain.ProductRepository
import com.dohyundev.pos.core.catalog.product.dto.ProductCommand
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ProductCommandService(
    private val productRepository: ProductRepository
) {
    fun create(command: ProductCommand.Create): Product {
        val product = Product(command.name, command.description, command.price, command.priority)
        return productRepository.save(product)
    }

    fun update(command: ProductCommand.Update): Product {
        val product = productRepository.findById(command.productId)
            .orElseThrow { IllegalArgumentException("Product not found") }
        command.name?.let { product.name = it }
        command.description?.let { product.description = it }
        command.price?.let { product.price = it }
        command.priority?.let { product.priority = it }
        return product
    }

    fun delete(command: ProductCommand.Delete) {
        productRepository.deleteById(command.productId)
    }
}
