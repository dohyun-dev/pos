package com.dohyundev.pos.core.discount

import java.math.BigDecimal

enum class DiscountPolicyType {
    FIXED {
        override fun apply(value: BigDecimal, amount: BigDecimal): BigDecimal {
            val result = amount - value
            return if (result < BigDecimal.ZERO) BigDecimal.ZERO else result
        }
    },

    RATE {
        override fun apply(value: BigDecimal, amount: BigDecimal): BigDecimal {
            val discount = amount.multiply(value).divide(BigDecimal(100))
            val result = amount - discount
            return if (result < BigDecimal.ZERO) BigDecimal.ZERO else result
        }
    };

    abstract fun apply(value: BigDecimal, amount: BigDecimal): BigDecimal
}
