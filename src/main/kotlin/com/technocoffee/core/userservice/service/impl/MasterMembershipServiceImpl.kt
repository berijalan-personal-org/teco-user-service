package com.technocoffee.core.userservice.service.impl

import com.technocoffee.core.userservice.domain.dto.response.ResMembershipDto
import com.technocoffee.core.userservice.repository.MasterMembershipRepository
import com.technocoffee.core.userservice.service.MasterMembershipService
import org.springframework.stereotype.Service

@Service
class MasterMembershipServiceImpl (
    private val masterMembershipRepository: MasterMembershipRepository
): MasterMembershipService {
    override fun getAll(): List<ResMembershipDto> {
        val rawMembership = masterMembershipRepository.findAll()
        val result = mutableListOf< ResMembershipDto>()
        rawMembership.forEach { membership ->
            result.add(
                ResMembershipDto(
                    id = membership.id,
                    name = membership.name
                )
            )
        }
        return result
    }
}