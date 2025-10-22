package com.dohyundev.common.vo

import jakarta.persistence.Embeddable
import java.time.LocalTime

@Embeddable
data class TimeRange(
    val start: LocalTime? = null,
    val end: LocalTime? = null,
) {
}