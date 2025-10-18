package com.dohyundev.pos.core.discount

import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/discount-policies")
class DiscountPolicyApiControllerV1(
    private val discountPolicyQueryService: DiscountPolicyQueryServiceV1,
    private val discountPolicyCommandService: DiscountPolicyCommandServiceV1
) {
    @GetMapping
    fun getDiscountPolicies(): ResponseEntity<List<Any>> {
        //TODO
        return ResponseEntity.ok().build()
    }

    @PostMapping
    fun createDiscountPolicy(
        @Valid @RequestBody request: DiscountPolicyCommand.Create
    ): ResponseEntity<Any> {
        discountPolicyCommandService.createDiscountPolicy(request)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/{discount-policy-id}")
    fun updateDiscountPolicy(
        @PathVariable discountPolicyId: String,
        @Valid @RequestBody request: DiscountPolicyCommand.Update
    ): ResponseEntity<Any> {
        discountPolicyCommandService.updateDiscountPolicy(request)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{discount-policy-id}")
    fun deleteCreatePolicy(
        @PathVariable discountPolicyId: String,
    ): ResponseEntity<Any> {
        discountPolicyCommandService.deleteDiscountPolicy(discountPolicyId)
        return ResponseEntity.ok().build()
    }
}