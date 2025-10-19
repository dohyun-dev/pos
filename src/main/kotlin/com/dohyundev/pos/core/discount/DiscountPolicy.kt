package com.dohyundev.pos.core.discount

import com.dohyundev.common.entity.TsidBaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.math.BigDecimal

@Entity
class DiscountPolicy(
    @Column(nullable = false, unique = true)
    var name: String,

    var description: String? = null,

    @Column(nullable = false)
    var value: BigDecimal,

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var type: DiscountPolicyType,
) : TsidBaseEntity() {

    fun update(name: String, description: String?, value: BigDecimal, type: DiscountPolicyType) {
        this.name = name
        this.description = description
        this.value = value
        this.type = type
    }
}
