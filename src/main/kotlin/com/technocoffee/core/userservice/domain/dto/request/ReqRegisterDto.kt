package com.technocoffee.core.userservice.domain.dto.request

import jakarta.validation.constraints.NotBlank

data class ReqRegisterDto(
    @field:NotBlank(message = "username is required")
    val name: String,

    @field:NotBlank(message = "email is required")
    val email: String,

    @field:NotBlank(message = "password is required")
    val password: String,

    val membershipId: Int?
)
