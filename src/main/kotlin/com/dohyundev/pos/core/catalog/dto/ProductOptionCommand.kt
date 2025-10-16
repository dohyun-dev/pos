package com.dohyundev.pos.core.catalog.dto

import com.dohyundev.pos.core.catalog.domain.entity.ProductOption
import com.dohyundev.pos.core.catalog.domain.entity.ProductOptionGroup
import jakarta.persistence.Column
import jakarta.validation.Valid
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PositiveOrZero
import java.math.BigDecimal

interface ProductOptionCommand {
    data class CreateProductOptionGroup(
        @field:NotBlank(message = "옵션 그룹 이름은 필수입니다.")
        val name: String? = null,
        val description: String? = null,
        @NotNull(message = "필수 선택 여부는 필수입니다.")
        val isRequired: Boolean? = null,
        @NotNull(message = "선택 가능 옵션 개수는 필수입니다.")
        @PositiveOrZero(message = "선택 가능 옵션 개수는 0 이상이어야 합니다..")
        val selectableOptionCount: Int? = null,
        @Valid
        val options: MutableList<CreateProductOption> = mutableListOf(),
    ) : ProductCommand {
        fun toEntity(): ProductOptionGroup {
            val productOptionGroup = ProductOptionGroup(
                name = name!!,
                description = description,
                isRequired = isRequired ?: false,
                selectableOptionCount = selectableOptionCount ?: 0,
            )
            val optionEntities = options.map { it.toEntity(productOptionGroup) }
            productOptionGroup.changeOptions(optionEntities)
            return productOptionGroup
        }
    }

    data class CreateProductOption(
        @field:NotBlank(message = "옵션 이름은 필수입니다.")
        val name: String? = null,
        val description: String? = null,
        @field:DecimalMin(value = "0.0", message = "추가금은 0 이상이어야 합니다.")
        val extraPrice: BigDecimal? = null,
    ) : ProductCommand {
        fun toEntity(group: ProductOptionGroup) = ProductOption(
            group = group,
            name = name!!,
            description = description,
            extraPrice = extraPrice ?: BigDecimal.ZERO,
        )
    }
}