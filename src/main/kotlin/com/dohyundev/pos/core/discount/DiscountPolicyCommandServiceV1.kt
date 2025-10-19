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
    fun createDiscountPolicy(command: DiscountPolicyCommand.Create): String {
        val discountPolicy = DiscountPolicy(
            name = command.name,
            description = command.description,
            value = command.value,
            type = command.type
        )
        
        return discountPolicyRepository.save(discountPolicy).id!!
    }

    @Transactional
    fun updateDiscountPolicy(discountPolicyId: String, command: DiscountPolicyCommand.Update) {
        val discountPolicy = discountPolicyRepository.findByIdOrNull(discountPolicyId) 
            ?: throw EntityNotFoundException("할인 정책을 찾을 수 없습니다. ID: $discountPolicyId")
        
        discountPolicy.update(
            name = command.name,
            description = command.description,
            value = command.value,
            type = command.type
        )
    }

    @Transactional
    fun deleteDiscountPolicy(discountPolicyId: String) {
        val discountPolicy = discountPolicyRepository.findByIdOrNull(discountPolicyId) 
            ?: throw EntityNotFoundException("할인 정책을 찾을 수 없습니다. ID: $discountPolicyId")
        
        discountPolicyRepository.delete(discountPolicy)
    }
}
