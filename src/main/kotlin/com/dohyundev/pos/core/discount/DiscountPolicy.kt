package com.dohyundev.pos.core.discount

import com.dohyundev.common.entity.TsidBaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.math.BigDecimal

@Entity
class DiscountPolicy(
    @Column(nullable = false, unique = true)
    var name: String,

    var description: String? = null,

    @Column(nullable = false)
    var value: BigDecimal,

    @Column(nullable = false)
    var type: DiscountPolicyType,
) : TsidBaseEntity() {
    fun applyDiscount(amount: BigDecimal): BigDecimal {
        return type.apply(value, amount)
    }
}
