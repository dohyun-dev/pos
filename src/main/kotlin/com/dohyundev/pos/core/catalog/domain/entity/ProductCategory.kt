package com.dohyundev.pos.core.catalog.domain.entity

import com.dohyundev.common.entity.Activatable
import com.dohyundev.common.entity.DisplayOrderable
import com.dohyundev.common.entity.SoftDeleteTsidBaseEntity
import com.dohyundev.common.entity.TsidBaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Version
import org.hibernate.annotations.SoftDelete
import org.hibernate.annotations.SoftDeleteType

@Entity
@Table(name = "product_categories")
@SoftDelete(columnName = "is_deleted", strategy = SoftDeleteType.DELETED)
class ProductCategory(
    @Column(nullable = false)
    var name: String,

    var description: String? = null,

    @Column(nullable = false)
    override var displayOrder: Long = 0,

    @Column(nullable = false)
    override var isActive: Boolean = true
) : SoftDeleteTsidBaseEntity<ProductCategory>(), DisplayOrderable, Activatable{
    @Version
    private var version: Long? = null

    fun update(name: String, description: String?) {
        this.name = name
        this.description = description
    }
}
