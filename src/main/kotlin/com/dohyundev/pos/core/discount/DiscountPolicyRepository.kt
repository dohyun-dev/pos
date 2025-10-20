package com.dohyundev.pos.core.discount

import org.springframework.data.jpa.repository.JpaRepository

interface DiscountPolicyRepository : JpaRepository<DiscountPolicy, Long> {
}