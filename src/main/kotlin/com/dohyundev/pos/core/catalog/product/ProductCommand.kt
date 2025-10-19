package com.dohyundev.pos.core.catalog.product

import com.dohyundev.common.enum.TaxType
import com.dohyundev.common.vo.MoveDirection
import jakarta.validation.constraints.*
import java.math.BigDecimal

interface ProductCommand {
    data class CreateProduct(
        @field:NotBlank(message = "카테고리 ID는 필수입니다.")
        val categoryId: String,
        
        @field:NotBlank(message = "제품명은 필수입니다.")
        @field:Size(max = 100, message = "제품명은 100자를 초과할 수 없습니다.")
        val name: String,
        
        @field:Size(max = 500, message = "설명은 500자를 초과할 수 없습니다.")
        val description: String? = null,
        
        @field:Size(max = 50, message = "바코드는 50자를 초과할 수 없습니다.")
        val barcode: String? = null,
        
        @field:NotNull(message = "기본 가격은 필수입니다.")
        @field:DecimalMin(value = "0", message = "기본 가격은 0 이상이어야 합니다.")
        val basePrice: BigDecimal,
        
        @field:NotNull(message = "세금 유형은 필수입니다.")
        val taxType: TaxType = TaxType.TAX,
        
        val optionGroupIds: List<String> = emptyList()
    )

    data class UpdateProduct(
        @field:NotBlank(message = "제품 ID는 필수입니다.")
        val productId: String,
        
        @field:NotBlank(message = "카테고리 ID는 필수입니다.")
        val categoryId: String,
        
        @field:NotBlank(message = "제품명은 필수입니다.")
        @field:Size(max = 100, message = "제품명은 100자를 초과할 수 없습니다.")
        val name: String,
        
        @field:Size(max = 500, message = "설명은 500자를 초과할 수 없습니다.")
        val description: String? = null,
        
        @field:Size(max = 50, message = "바코드는 50자를 초과할 수 없습니다.")
        val barcode: String? = null,
        
        @field:NotNull(message = "기본 가격은 필수입니다.")
        @field:DecimalMin(value = "0", message = "기본 가격은 0 이상이어야 합니다.")
        val basePrice: BigDecimal,
        
        @field:NotNull(message = "세금 유형은 필수입니다.")
        val taxType: TaxType = TaxType.TAX,
        
        val optionGroupIds: List<String> = emptyList()
    )

    data class ExchangeProductPosition(
        @field:NotBlank(message = "제품 ID는 필수입니다.")
        val productId: String,
        
        @field:NotNull(message = "이동 방향은 필수입니다.")
        val direction: MoveDirection
    )
}
