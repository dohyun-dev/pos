package com.dohyundev.pos.core.discount

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DiscountPolicyCommandServiceV1(
    private val discountPolicyRepository: DiscountPolicyRepository
) {
    @Transactional
    fun createDiscountPolicy(command: DiscountPolicyCommand.Create) {

    }

    @Transactional
    fun updateDiscountPolicy(discountPolicyId: String, command: DiscountPolicyCommand.Update) {

    }

    @Transactional
    fun deleteDiscountPolicy(discountPolicyId: String) {
        val discountPolicy = discountPolicyRepository.findByIdOrNull(discountPolicyId) ?: throw EntityNotFoundException()
        discountPolicyRepository.delete(discountPolicy)
    }
}
