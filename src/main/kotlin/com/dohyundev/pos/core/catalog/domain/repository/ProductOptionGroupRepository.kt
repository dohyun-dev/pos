package com.dohyundev.pos.core.catalog.domain.repository

import com.dohyundev.pos.core.catalog.domain.entity.ProductOptionGroup
import org.springframework.data.jpa.repository.JpaRepository

interface ProductOptionGroupRepository : JpaRepository<ProductOptionGroup, String> {
}