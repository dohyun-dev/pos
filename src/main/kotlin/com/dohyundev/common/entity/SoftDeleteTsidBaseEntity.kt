package com.dohyundev.common.entity

import com.dohyundev.common.converter.DeletedAtConverter
import io.hypersistence.utils.hibernate.id.Tsid
import jakarta.persistence.EntityListeners
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.SoftDelete
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
@SoftDelete(columnName = "deleted_at", converter = DeletedAtConverter::class)
abstract class SoftDeleteTsidBaseEntity<T : SoftDeleteTsidBaseEntity<T>> : BaseEntity<T>() {
    @Id
    @Tsid
    val id: String? = null
}
