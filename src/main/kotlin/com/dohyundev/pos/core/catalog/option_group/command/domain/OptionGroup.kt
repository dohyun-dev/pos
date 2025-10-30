package com.dohyundev.pos.core.catalog.option_group.command.domain

import com.dohyundev.common.entity.BaseEntity
import com.dohyundev.common.entity.DisplayOrderable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
@Table(name = "option_groups")
class OptionGroup(
    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var isRequired: Boolean = false,

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    var choices: List<Option> = emptyList(),

    @Column(nullable = false)
    var minChoices: Int = 0,

    @Column(nullable = false)
    var maxChoices: Int = 1,

    @Column(nullable = false)
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