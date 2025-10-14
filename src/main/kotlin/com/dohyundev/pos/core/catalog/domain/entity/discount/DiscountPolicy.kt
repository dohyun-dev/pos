package com.dohyundev.pos.core.catalog.domain.entity.discount

import com.dohyundev.common.entity.TsidBaseEntity
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discount_type", discriminatorType = DiscriminatorType.STRING)
abstract class DiscountPolicy(
    @Column(nullable = false, unique = true, length = 100)
    var name: String,
    var description: String? = null,
    var active: Boolean = true
) : TsidBaseEntity<DiscountPolicy>() {
    abstract fun applyDiscount(amount: BigDecimal): BigDecimal
}
