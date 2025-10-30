package com.dohyundev.pos.core.catalog.option_group.command.domain

import com.dohyundev.common.entity.BaseEntity
import com.dohyundev.common.entity.DisplayOrderable
import jakarta.persistence.*

@Entity
@Table(name = "option_groups")
class OptionGroup(
    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var isRequired: Boolean = false,

    @OneToMany(mappedBy = "optionGroup", cascade = [CascadeType.ALL], orphanRemoval = true)
    var choices: MutableList<Option> = mutableListOf(),

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
        changeChoices(choices)
        this.minChoices = minChoices
        this.maxChoices = maxChoices
        this.displayOrder = displayOrder
    }

    fun changeChoices(choices: Collection<Option>) {
        this.choices.clear()
        this.choices.addAll(choices)
    }
}