package com.dohyundev.common.exception

open class BusinessException(
    val code: Int? = null,
    message: String? = null,
    cause: Throwable? = null,
): RuntimeException(
    message = message,
    cause = cause
) {
}