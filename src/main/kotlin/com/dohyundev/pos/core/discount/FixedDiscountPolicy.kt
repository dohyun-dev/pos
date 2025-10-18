package com.dohyundev.pos.core.discount

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity
import java.math.BigDecimal

@Entity
@DiscriminatorValue("FIXED")
class FixedDiscountPolicy(
    name: String,
    description: String?,
    amount: BigDecimal,
) : DiscountPolicy(
    name = name,
    description = description,
    amount = amount
) {
}
