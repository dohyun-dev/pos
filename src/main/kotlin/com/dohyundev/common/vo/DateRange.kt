package com.dohyundev.common.vo

import jakarta.persistence.Embeddable
import java.time.LocalDate

@Embeddable
data class DateRange(
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null
) {
}