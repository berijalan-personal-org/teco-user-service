package com.technocoffee.core.userservice.domain.dto.response

import java.io.Serializable

data class ResUserDto(
    val id: Int,
    val name: String,
    val email: String,
    var membershipId: Int?
): Serializable {
    // gunakan ini untuk caching redis
    companion object {
        private const val serialVersionUID: Long = 355401288680967524L
    }
}
