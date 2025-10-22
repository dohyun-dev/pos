package com.dohyundev.pos.core.catalog.discount

import com.dohyundev.common.entity.Activatable
import com.dohyundev.common.entity.DisplayOrderable
import com.dohyundev.common.entity.TsidBaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.math.BigDecimal

@Entity
class Discount(
    @Column(nullable = false, unique = true)
    var title: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var type: DiscountType,

    @Column(nullable = false)
    var amount: BigDecimal = BigDecimal.ZERO,

    override var displayOrder: Long,

    override var isActive: Boolean
): TsidBaseEntity(), DisplayOrderable, Activatable {
}