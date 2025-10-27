package com.dohyundev.common.entity

import io.hypersistence.utils.hibernate.id.Tsid
import jakarta.persistence.EntityListeners
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.domain.AbstractAggregateRoot
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
abstract class BaseEntity : AbstractAggregateRoot<BaseEntity>() {
    @Id
    @Tsid
    var id: Long? = null

    @CreatedDate
    var createdAt: LocalDateTime? = null

    @LastModifiedDate
    var modifiedAt: LocalDateTime? = null

    @CreatedBy
    var createdBy: Long? = null

    @LastModifiedBy
    var modifiedBy: Long? = null
}
