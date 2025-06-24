package com.technocoffee.core.userservice.controller

import com.technocoffee.core.userservice.domain.dto.response.ResMembershipDto
import com.technocoffee.core.userservice.service.MasterMembershipService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping

class MembershipController (
    private val masterMembershipService: MasterMembershipService
) {
    @GetMapping("/memberships")
    fun getAll(): ResponseEntity<List<ResMembershipDto>> {
        return ResponseEntity.ok(
            masterMembershipService.getAll()
        )
    }
}