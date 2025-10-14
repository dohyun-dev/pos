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
        val category: ProductCategory? = null
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
    data class CreateOptionGroup(
        @field:NotBlank(message = "옵션 그룹 이름은 필수입니다.")
        val name: String? = null,
        val description: String? = null,
        val isRequired: Boolean? = null,
        val isMultiSelect: Boolean? = null,
        val displayOrder: Int? = null
    ) : ProductCommand {
        fun toEntity(product: Product) = ProductOptionGroup(
            product = product,
            name = name!!,
            description = description,
            isRequired = isRequired ?: false,
            isMultiSelect = isMultiSelect ?: false,
            displayOrder = displayOrder ?: 0
        )
    }

    data class CreateOption(
        @field:NotBlank(message = "옵션 이름은 필수입니다.")
        val name: String? = null,
        val description: String? = null,
        @field:DecimalMin(value = "0.0", message = "추가금은 0 이상이어야 합니다.")
        val extraPrice: BigDecimal? = null,
        val defaultSelected: Boolean? = null,
        val displayOrder: Int? = null
    ) : ProductCommand {
        fun toEntity(group: ProductOptionGroup) = ProductOption(
            group = group,
            name = name!!,
            description = description,
            extraPrice = extraPrice ?: BigDecimal.ZERO,
            defaultSelected = defaultSelected ?: false,
            displayOrder = displayOrder ?: 0
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
        val uom: String? = null
    ) : ProductCommand

    /** 옵션 그룹 수정 Command */
    data class UpdateOptionGroup(
        @field:NotBlank(message = "옵션 그룹 이름은 필수입니다.")
        val name: String? = null,
        val description: String? = null,
        val isRequired: Boolean? = null,
        val isMultiSelect: Boolean? = null,
        val displayOrder: Int? = null
    ) : ProductCommand

    /** 옵션 수정 Command */
    data class UpdateOption(
        @field:NotBlank(message = "옵션 이름은 필수입니다.")
        val name: String? = null,
        val description: String? = null,
        @field:DecimalMin(value = "0.0", message = "추가금은 0 이상이어야 합니다.")
        val extraPrice: BigDecimal? = null,
        val defaultSelected: Boolean? = null,
        val displayOrder: Int? = null
    ) : ProductCommand
}
