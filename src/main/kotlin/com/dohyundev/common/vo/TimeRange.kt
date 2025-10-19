package com.dohyundev.common.vo

import jakarta.persistence.Embeddable
import java.time.LocalTime

@Embeddable
data class TimeRange(
    val startTime: LocalTime? = null,
    val endTime: LocalTime? = null,
) {
}