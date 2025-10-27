package com.dohyundev.pos.core.catalog.option_group.command.domain

import jakarta.persistence.Embeddable

@Embeddable
data class Option(
    val name: String,
    val price: Long,
    val isDefault: Boolean = false,
    val state: OptionState = OptionState.ON_SALE,
    val displayOrder: Int
) {
}