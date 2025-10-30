package com.dohyundev.pos.core.order

import com.dohyundev.common.entity.BaseEntity
import com.dohyundev.pos.core.payment.Payment
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany
import java.time.LocalDateTime

@Entity
class Order(
    @Column(nullable = false)
    val orderTime: LocalDateTime,

    @OneToMany(mappedBy = "order",cascade = [CascadeType.ALL], orphanRemoval = true)
    val orderLines: List<OrderLine>,

    val totalPrice: Long,

    @OneToMany(mappedBy = "order",cascade = [CascadeType.ALL])
    val payments: List<Payment> = mutableListOf(),
): BaseEntity() {
}