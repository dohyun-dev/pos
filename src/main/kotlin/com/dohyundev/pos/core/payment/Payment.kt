package com.dohyundev.pos.core.payment

import com.dohyundev.common.entity.BaseEntity
import com.dohyundev.pos.core.order.Order
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Payment(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    val order: Order,


): BaseEntity() {
}