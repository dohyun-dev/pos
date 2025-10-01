package com.dohyundev.pos.core.catalog.option.domain

import com.dohyundev.pos.core.catalog.option_group.domain.OptionGroup
import com.dohyundev.pos.core.common.entity.TsidBaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne

@Entity
class Option(
    var name: String,
    var description: String?,
    var priority: Int = 0,
    var price: Long = 0,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_group_id")
    val optionGroup: OptionGroup
) : TsidBaseEntity<Option>() {
}