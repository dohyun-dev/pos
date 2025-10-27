package com.dohyundev.pos.core.catalog.category.command.domain

import com.dohyundev.common.entity.Activatable
import com.dohyundev.common.entity.BaseEntity
import com.dohyundev.common.entity.DisplayOrderable
import com.dohyundev.pos.core.catalog.category.command.event.PersistCategoryEvent
import com.dohyundev.pos.core.catalog.category.command.event.RemoveCategoryEvent
import com.dohyundev.pos.core.catalog.category.command.event.UpdateCategoryEvent
import jakarta.persistence.*

@Entity
class Category(
    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    override var displayOrder: Int = 0,

    @Column(nullable = false)
    override var active: Boolean = true
) : BaseEntity(), DisplayOrderable, Activatable{
    @Version
    private var version: Long? = null

    @PrePersist
    fun prePersist() {
        this.andEvent(PersistCategoryEvent(this))
    }

    @PreUpdate
    fun preUpdate() {
        this.andEvent(UpdateCategoryEvent(this))
    }

    @PreRemove
    fun preRemove() {
        this.andEvent(RemoveCategoryEvent(this))
    }

    fun update(
        name: String = this.name,
        displayOrder: Int = this.displayOrder,
        isActive: Boolean = this.active
    ) {
        this.name = name
        changeDisplayOrder(displayOrder)
        this.active = isActive
    }
}
