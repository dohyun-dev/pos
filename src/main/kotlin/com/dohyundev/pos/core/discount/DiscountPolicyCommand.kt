package com.dohyundev.pos.core.discount

import jakarta.validation.constraints.*
import java.math.BigDecimal

interface DiscountPolicyCommand {
    data class Create(
        @field:NotBlank(message = "할인 정책 이름은 필수입니다")
        @field:Size(max = 100, message = "할인 정책 이름은 100자를 초과할 수 없습니다")
        val name: String?,

        @field:Size(max = 500, message = "설명은 500자를 초과할 수 없습니다")
        val description: String? = null,

        @field:NotNull(message = "할인 값은 필수입니다")
        @field:DecimalMin(value = "0", message = "할인 값은 0 이상이어야 합니다")
        val value: BigDecimal?,

        @field:NotNull(message = "할인 타입은 필수입니다")
        val type: DiscountPolicyType?
    )

    data class Update(
        @field:NotBlank(message = "할인 정책 이름은 필수입니다")
        @field:Size(max = 100, message = "할인 정책 이름은 100자를 초과할 수 없습니다")
        val name: String?,

        @field:Size(max = 500, message = "설명은 500자를 초과할 수 없습니다")
        val description: String? = null,

        @field:NotNull(message = "할인 값은 필수입니다")
        @field:DecimalMin(value = "0", message = "할인 값은 0 이상이어야 합니다")
        val value: BigDecimal?,

        @field:NotNull(message = "할인 타입은 필수입니다")
        val type: DiscountPolicyType?
    )
}
