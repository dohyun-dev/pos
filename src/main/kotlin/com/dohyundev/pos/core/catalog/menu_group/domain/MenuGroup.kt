package com.dohyundev.pos.core.catalog.menu_group.domain

import com.dohyundev.pos.core.common.entity.TsidBaseEntity
import jakarta.persistence.Entity

@Entity
class MenuGroup(
    var name: String,
    var priority: Int = 0,
    var isVisible: Boolean = true,
) : TsidBaseEntity<MenuGroup>() {
}