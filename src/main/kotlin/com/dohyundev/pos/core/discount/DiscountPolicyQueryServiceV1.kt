package com.dohyundev.pos.core.discount

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class DiscountPolicyQueryServiceV1(
    private val discountPolicyRepository: DiscountPolicyRepository
) {
    fun getDiscountPolicies(): List<DiscountPolicyResponse.Summary> {
        return discountPolicyRepository.findAll()
            .map { DiscountPolicyResponse.Summary.from(it) }
    }

    fun getDiscountPolicy(discountPolicyId: String): DiscountPolicyResponse.Detail {
        val discountPolicy = discountPolicyRepository.findByIdOrNull(discountPolicyId)
            ?: throw EntityNotFoundException("할인 정책을 찾을 수 없습니다. ID: $discountPolicyId")
        
        return DiscountPolicyResponse.Detail.from(discountPolicy)
    }
}
