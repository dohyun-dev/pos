package com.dohyundev.common.vo

import jakarta.persistence.Embeddable
import java.time.LocalDate

@Embeddable
data class DateRange(
    val from: LocalDate? = null,
    val to: LocalDate? = null
) {
    /** 기간 문자열 표시 (예: 2025-01-01 ~ 2025-12-31) */
    override fun toString(): String {
        return if (to != null) "$from ~ $to" else "$from ~ (무기한)"
    }
}