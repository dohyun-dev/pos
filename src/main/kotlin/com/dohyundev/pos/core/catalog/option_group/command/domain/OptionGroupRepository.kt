package com.dohyundev.pos.core.catalog.option_group.command.domain

import org.springframework.data.jpa.repository.JpaRepository

interface OptionGroupRepository : JpaRepository<OptionGroup, Long> {
}