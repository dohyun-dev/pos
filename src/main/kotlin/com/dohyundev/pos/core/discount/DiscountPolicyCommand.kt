package com.dohyundev.pos.core.discount

interface DiscountPolicyCommand {
    data class Create(
        val name: String?
    ) {
    }

    data class Update(
        val name: String?
    )
}
