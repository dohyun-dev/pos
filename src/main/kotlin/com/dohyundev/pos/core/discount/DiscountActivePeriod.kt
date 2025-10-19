package com.dohyundev.pos.core.discount

import jakarta.persistence.*
import java.time.DayOfWeek

@Embeddable
data class DiscountActivePeriod(
    /** 적용 요일 */
    @ElementCollection
    @CollectionTable(name = "discount_active_day", joinColumns = [JoinColumn(name = "rule_id")])
    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    val daysOfWeek: Set<DayOfWeek> = emptySet(),

    /** 하루 중 적용 시간대 (여러 구간 가능) */
    @ElementCollection
    @CollectionTable(name = "discount_active_time", joinColumns = [JoinColumn(name = "rule_id")])
    val timeSlots: Set<DiscountTimeSlot> = emptySet(),

    /** 전체 적용 기간 (null이면 무기한) */
    @Embedded
    val dateRange: DiscountDateRange? = null
) {
    /** 현재 시각이 이 조건에 맞는지 검사 */
    fun isActive(now: LocalDateTime = LocalDateTime.now()): Boolean {
        if (!enabled) return true

        val dayMatch = daysOfWeek.isEmpty() || daysOfWeek.contains(now.dayOfWeek)
        val dateMatch = dateRange?.contains(now.toLocalDate()) ?: true
        val timeMatch = timeSlots.isEmpty() || timeSlots.any { it.contains(now.toLocalTime()) }

        return dayMatch && dateMatch && timeMatch
    }
}
