package com.technocoffee.core.userservice.repository

import com.technocoffee.core.userservice.domain.entity.MasterUserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional

interface MasterUserRepository: JpaRepository<MasterUserEntity, Int> {
    fun findFirstByEmail(email: String?): MasterUserEntity?
}