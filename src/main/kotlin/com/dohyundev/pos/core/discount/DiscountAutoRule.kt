package com.dohyundev.pos.core.discount

import com.dohyundev.common.entity.TsidBaseEntity
import com.dohyundev.common.vo.DateRange
import com.dohyundev.common.vo.TimeRange
import jakarta.persistence.*
import java.time.DayOfWeek

@Entity
class DiscountAutoRule(
    @ElementCollection
    @CollectionTable(name = "discount_day_of_week", joinColumns = [JoinColumn(name = "rule_id")])
    @Column(name = "day_of_week")
    @Enumerated(EnumType.STRING)
    val daysOfWeekCondition: Set<DayOfWeek> = DayOfWeek.entries.toSet(),

    @Embedded
    var dateRangeCondition: DateRange? = null,

    @Embedded
    var timeRangeCondition: TimeRange? = null,
) : TsidBaseEntity() {

}