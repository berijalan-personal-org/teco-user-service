package com.technocoffee.core.userservice.domain.dto.request

import jakarta.validation.constraints.NotBlank

data class ReqLoginDto(
    @field:NotBlank(message = "email is required")
    val email: String,

    @field:NotBlank(message = "password is required")
    val password: String
)
