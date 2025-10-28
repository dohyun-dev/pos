package com.dohyundev.common.exception

open class CustomEntityNotFoundException(code: Int? = null, message: String? = null, cause: Throwable? = null) :
    BusinessException(code, message, cause) {
}