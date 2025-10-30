package com.dohyundev.pos.core.catalog.product.command.application

import com.dohyundev.pos.core.catalog.product.command.domain.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductCommandServiceV1(
    private val productRepository: ProductRepository,
) {
}