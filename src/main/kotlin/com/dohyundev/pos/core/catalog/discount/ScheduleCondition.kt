package com.dohyundev.pos.core.catalog.discount

import com.dohyundev.common.vo.DateRange
import com.dohyundev.common.vo.TimeRange
import java.time.DayOfWeek

data class ScheduleCondition(
    val dayOfWeek: Set<DayOfWeek>,
    val timeRange: TimeRange,
    val dateRange: DateRange
)
