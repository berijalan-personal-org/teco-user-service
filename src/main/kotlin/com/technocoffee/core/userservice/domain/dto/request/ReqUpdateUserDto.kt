package com.technocoffee.core.userservice.domain.dto.request

data class ReqUpdateUserDto(
    val email: String?,
    val name: String?,
    val password: String?,
    val membershipId: Int?
)
