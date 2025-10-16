package com.dohyundev.common.converter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import java.time.LocalDateTime

@Converter
class DeletedAtConverter : AttributeConverter<Boolean, LocalDateTime>{
    override fun convertToDatabaseColumn(p0: Boolean?): LocalDateTime = LocalDateTime.now()

    override fun convertToEntityAttribute(p0: LocalDateTime?): Boolean = p0 != null
}