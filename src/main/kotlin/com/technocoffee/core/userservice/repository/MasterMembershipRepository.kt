package com.technocoffee.core.userservice.repository

import com.technocoffee.core.userservice.domain.entity.MasterMembershipEntity
import org.springframework.data.jpa.repository.JpaRepository

interface MasterMembershipRepository: JpaRepository<MasterMembershipEntity, Int> {
}