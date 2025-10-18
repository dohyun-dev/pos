package com.dohyundev.common.entity

import io.hypersistence.utils.hibernate.id.Tsid
import jakarta.persistence.EntityListeners
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.data.jpa.domain.support.AuditingEntityListener

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
abstract class TsidBaseEntity : BaseEntity<TsidBaseEntity>() {
    @Id
    @Tsid
    val id: String? = null
}
