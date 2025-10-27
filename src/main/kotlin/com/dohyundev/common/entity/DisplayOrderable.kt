package com.dohyundev.common.entity

interface DisplayOrderable {
    var displayOrder: Int

    fun changeDisplayOrder(order: Int) {
        this.displayOrder = order
    }

    fun swapDisplayOrder(other: DisplayOrderable) {
        val temp = this.displayOrder
        this.displayOrder = other.displayOrder
        other.displayOrder = temp
    }
}
