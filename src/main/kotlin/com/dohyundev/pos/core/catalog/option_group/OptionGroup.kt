package com.dohyundev.pos.core.catalog.option_group

import com.dohyundev.common.entity.Activatable
import com.dohyundev.common.entity.DisplayOrderable
import com.dohyundev.common.entity.TsidBaseEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany

@Entity
class OptionGroup(
    @Column(nullable = false)
    var name: String,

    var description: String? = null,

    @Column(nullable = false)
    var isRequired: Boolean = false,

    @Column(nullable = false)
    var selectableOptionCount: Int = 1,

    @OneToMany(mappedBy = "group", cascade = [CascadeType.ALL], orphanRemoval = true)
    var options: MutableList<Option> = mutableListOf(),

    @Column(nullable = false)
    override var displayOrder: Long = 0,

    @Column(nullable = false)
    override var isActive: Boolean = true
) : TsidBaseEntity(), DisplayOrderable, Activatable {

    fun update(
        name: String = this.name,
        description: String? = this.description,
        isRequired: Boolean = this.isRequired,
        selectableOptionCount: Int = this.selectableOptionCount,
        displayOrder: Long = this.displayOrder,
        options: Collection<Option> = this.options,
        isActive: Boolean = this.isActive,
    ) {
        this.name = name
        this.description = description
        this.isRequired = isRequired
        this.selectableOptionCount = selectableOptionCount
        this.displayOrder = displayOrder
        changeOptions(options)
        this.isActive = isActive
    }

    fun changeOptions(options: Collection<Option>) {
        this.options.clear()
        this.options.addAll(options)
    }
}
