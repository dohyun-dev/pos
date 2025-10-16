package com.dohyundev.common.entity

interface DisplayOrderable {
    var displayOrder: Long

    fun changeDisplayOrder(order: Long) {
        this.displayOrder = order
    }

    fun swapDisplayOrder(other: DisplayOrderable) {
        val temp = this.displayOrder
        this.displayOrder = other.displayOrder
        other.displayOrder = temp
    }
}
