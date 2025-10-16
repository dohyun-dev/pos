package com.dohyundev.pos.core.catalog.domain.entity

import com.dohyundev.common.entity.Activatable
import com.dohyundev.common.entity.DisplayOrderable
import com.dohyundev.common.entity.SoftDeleteTsidBaseEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToMany

@Entity
class ProductOptionGroup(
    @Column(nullable = false)
    var name: String,

    var description: String? = null,

    @Column(nullable = false)
    var isRequired: Boolean = false,

    @Column(nullable = false)
    var selectableOptionCount: Int = 1,

    @OneToMany(mappedBy = "group", cascade = [CascadeType.ALL], orphanRemoval = true)
    var options: MutableList<ProductOption> = mutableListOf(),

    @Column(nullable = false)
    override var displayOrder: Long = 0,

    @Column(nullable = false)
    override var isActive: Boolean = true
) : SoftDeleteTsidBaseEntity<ProductOptionGroup>(), DisplayOrderable, Activatable {
    fun update(
        name: String,
        description: String? = null,
        isRequired: Boolean?,
        selectableOptionCount: Int,
        options: MutableList<ProductOption> = mutableListOf(),
    ) {
        this.name = name
        this.isRequired = isRequired ?: this.isRequired
        this.description = description
        this.selectableOptionCount = selectableOptionCount
        this.options = options
    }

    fun changeOptions(options: Collection<ProductOption>) {
        this.options.clear()
        this.options.addAll(options)
        this.options.forEachIndexed { index, option ->
            option.changeDisplayOrder(index.toLong())
        }
    }
}
