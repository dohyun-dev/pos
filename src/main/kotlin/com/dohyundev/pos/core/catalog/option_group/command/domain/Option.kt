package com.dohyundev.pos.core.catalog.option_group.command.domain

import com.dohyundev.common.entity.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "options")
data class Option(
    var name: String,
    var price: Long,
    var isDefault: Boolean = false,
    var state: OptionState = OptionState.ON_SALE,
    val displayOrder: Int
): BaseEntity() {
}