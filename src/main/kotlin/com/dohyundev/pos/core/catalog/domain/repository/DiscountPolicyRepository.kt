package com.dohyundev.pos.core.catalog.domain.repository

import com.dohyundev.pos.core.catalog.domain.entity.discount.DiscountPolicy
import org.springframework.data.jpa.repository.JpaRepository

interface DiscountPolicyRepository : JpaRepository<DiscountPolicy, String> {
}