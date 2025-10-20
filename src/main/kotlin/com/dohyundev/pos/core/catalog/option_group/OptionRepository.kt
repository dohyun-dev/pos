package com.dohyundev.pos.core.catalog.option_group

import org.springframework.data.jpa.repository.JpaRepository

interface OptionRepository : JpaRepository<Option, Long> {
}