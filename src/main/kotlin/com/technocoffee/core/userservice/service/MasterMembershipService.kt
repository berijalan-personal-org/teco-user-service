package com.technocoffee.core.userservice.service

import com.technocoffee.core.userservice.domain.dto.response.ResMembershipDto

interface MasterMembershipService {
    fun getAll(): List<ResMembershipDto>
}