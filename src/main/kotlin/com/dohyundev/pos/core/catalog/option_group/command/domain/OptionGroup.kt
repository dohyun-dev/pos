package com.dohyundev.pos.core.catalog.option_group.command.domain

import com.dohyundev.common.entity.BaseEntity
import com.dohyundev.common.entity.DisplayOrderable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
class OptionGroup(
    var name: String,

    var isRequired: Boolean = false,

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    var choices: List<Option> = emptyList(),

    var minChoices: Int = 0,

    var maxChoices: Int = 1,

    override var displayOrder: Int
): BaseEntity(), DisplayOrderable {
    fun update(
        name: String = this.name,
        isRequired: Boolean = this.isRequired,
        choices: List<Option> = this.choices,
        minChoices: Int = this.minChoices,
        maxChoices: Int = this.maxChoices,
        displayOrder: Int
    ) {
        this.name = name
        this.isRequired = isRequired
        this.choices = choices
        this.minChoices = minChoices
        this.maxChoices = maxChoices
        this.displayOrder = displayOrder
    }
}