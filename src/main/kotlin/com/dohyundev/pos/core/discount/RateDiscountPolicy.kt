package com.dohyundev.pos.core.discount

import com.dohyundev.common.entity.TsidBaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import java.math.BigDecimal

@Entity
abstract class RateDiscountPolicy(
    @Column(nullable = false, unique = true)
    var name: String,
    var description: String? = null,
    @Column(nullable = false)
    var amount: BigDecimal,
) : TsidBaseEntity() {
}
