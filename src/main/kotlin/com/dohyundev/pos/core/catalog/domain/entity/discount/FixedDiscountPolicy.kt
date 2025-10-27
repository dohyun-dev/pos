package com.dohyundev.pos.core.catalog.domain.entity.discount

import jakarta.persistence.Column
import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import java.math.BigDecimal

@Entity
@DiscriminatorValue("FIXED")
class FixedDiscountPolicy(
    name: String,
    description: String? = null,
    active: Boolean = true,
    @Column(precision = 15, scale = 2, nullable = false)
    val fixedAmount: BigDecimal
) : DiscountPolicy(name, description,active) {

    override fun applyDiscount(amount: BigDecimal): BigDecimal {
        if (fixedAmount <= BigDecimal.ZERO) return amount
        return amount.subtract(fixedAmount).max(BigDecimal.ZERO)
    }
}
