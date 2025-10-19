package com.dohyundev.pos.core.discount

import java.math.BigDecimal
import java.time.LocalDateTime

interface DiscountPolicyResponse {
    data class Detail(
        val id: String,
        val name: String,
        val description: String?,
        val value: BigDecimal,
        val type: DiscountPolicyType,
        val createdAt: LocalDateTime?,
        val modifiedAt: LocalDateTime?
    ) {
        companion object {
            fun from(discountPolicy: DiscountPolicy): Detail {
                return Detail(
                    id = discountPolicy.id!!,
                    name = discountPolicy.name,
                    description = discountPolicy.description,
                    value = discountPolicy.value,
                    type = discountPolicy.type,
                    createdAt = discountPolicy.createdAt,
                    modifiedAt = discountPolicy.modifiedAt
                )
            }
        }
    }

    data class Summary(
        val id: String,
        val name: String,
        val description: String?,
        val value: BigDecimal,
        val type: DiscountPolicyType
    ) {
        companion object {
            fun from(discountPolicy: DiscountPolicy): Summary {
                return Summary(
                    id = discountPolicy.id!!,
                    name = discountPolicy.name,
                    description = discountPolicy.description,
                    value = discountPolicy.value,
                    type = discountPolicy.type
                )
            }
        }
    }
}
