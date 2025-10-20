package com.dohyundev.pos.core.discount

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/api/v1/discount-policies")
class DiscountPolicyApiControllerV1(
    private val discountPolicyQueryService: DiscountPolicyQueryServiceV1,
    private val discountPolicyCommandService: DiscountPolicyCommandServiceV1
) {
    @GetMapping
    fun getDiscountPolicies(): ResponseEntity<List<DiscountPolicyResponse.Summary>> {
        val discountPolicies = discountPolicyQueryService.getDiscountPolicies()
        return ResponseEntity.ok(discountPolicies)
    }

    @GetMapping("/{discountPolicyId}")
    fun getDiscountPolicy(
        @PathVariable discountPolicyId: Long
    ): ResponseEntity<DiscountPolicyResponse.Detail> {
        val discountPolicy = discountPolicyQueryService.getDiscountPolicy(discountPolicyId)
        return ResponseEntity.ok(discountPolicy)
    }

    @PostMapping
    fun createDiscountPolicy(
        @Valid @RequestBody request: DiscountPolicyCommand.Create
    ): ResponseEntity<Void> {
        val discountPolicyId = discountPolicyCommandService.createDiscountPolicy(request)
        return ResponseEntity.created(URI.create("/api/v1/discount-policies/$discountPolicyId")).build()
    }

    @PutMapping("/{discountPolicyId}")
    fun updateDiscountPolicy(
        @PathVariable discountPolicyId: Long,
        @Valid @RequestBody request: DiscountPolicyCommand.Update
    ): ResponseEntity<Void> {
        discountPolicyCommandService.updateDiscountPolicy(discountPolicyId, request)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("/{discountPolicyId}")
    fun deleteDiscountPolicy(
        @PathVariable discountPolicyId: Long
    ): ResponseEntity<Void> {
        discountPolicyCommandService.deleteDiscountPolicy(discountPolicyId)
        return ResponseEntity.noContent().build()
    }
}
