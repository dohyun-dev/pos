package com.dohyundev.pos.core.catalog.dto

import com.dohyundev.pos.core.catalog.domain.entity.*
import jakarta.validation.constraints.*
import java.math.BigDecimal

sealed interface ProductCommand {
    data class CreateProduct(
        @field:NotBlank(message = "상품 코드는 필수입니다.")
        val code: String? = null,

        @field:NotBlank(message = "상품명은 필수입니다.")
        val name: String? = null,

        @field:DecimalMin(value = "0.0", message = "가격은 0 이상이어야 합니다.")
        val basePrice: BigDecimal? = null,

        val barcode: String? = null,
        val uom: String? = null,
        val description: String? = null,
        val category: ProductCategory? = null,

        val optionGroupIds: List<String> = emptyList(),
    ) : ProductCommand {
        fun toEntity(): Product = Product(
            code = code!!,
            name = name!!,
            basePrice = basePrice ?: BigDecimal.ZERO,
            barcode = barcode,
            uom = uom,
            description = description,
            category = category
        )
    }
    /** 상품 수정 Command */
    data class UpdateProduct(
        @field:NotBlank(message = "상품 코드는 필수입니다.")
        val code: String? = null,
        val barcode: String? = null,
        @field:NotBlank(message = "상품명은 필수입니다.")
        val name: String? = null,
        val description: String? = null,
        @field:DecimalMin(value = "0.0", message = "가격은 0 이상이어야 합니다.")
        val basePrice: BigDecimal? = null,
        val uom: String? = null,
        val optionGroupIds: List<String> = emptyList(),
    ) : ProductCommand
}
