package com.dohyundev.pos.core.catalog.product.command.dto

import com.dohyundev.pos.core.catalog.category.dto.CategoryDto
import com.dohyundev.pos.core.catalog.product.command.domain.ProductState
import java.io.Serializable
import java.time.LocalDateTime

/**
 * DTO for {@link com.dohyundev.pos.core.catalog.product.command.domain.Product}
 */
data class ProductDto(
    val id: Long? = null,
    val name: String? = null,
    val description: String? = null,
    val category: CategoryDto? = null,
    val prices: MutableList<ProductPriceDto> = mutableListOf(),
    val state: ProductState = ProductState.ON_SALE,
    val active: Boolean = true,
    val createdAt: LocalDateTime? = null,
    val modifiedAt: LocalDateTime? = null,
    val createdBy: Long? = null,
    val modifiedBy: Long? = null,
) : Serializable