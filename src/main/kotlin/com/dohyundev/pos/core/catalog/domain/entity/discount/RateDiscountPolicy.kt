package com.dohyundev.pos.core.catalog.domain.entity.discount

import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import java.math.BigDecimal

@Entity
@DiscriminatorValue("RATE")
class RateDiscountPolicy(
    name: String,
    description: String? = null,
    active: Boolean = true,
    @Column(precision = 10, scale = 4, nullable = false)
    val discountRate: BigDecimal
) : DiscountPolicy(name, description, active) {
    override fun applyDiscount(amount: BigDecimal): BigDecimal {
        if (discountRate <= BigDecimal.ZERO) return amount
        val discount = amount.multiply(discountRate)
        return amount.subtract(discount).max(BigDecimal.ZERO)
    }
}