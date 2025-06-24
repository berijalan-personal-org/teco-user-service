package com.technocoffee.core.userservice.exception

class CustomException(
    val exceptionMessage: String,
    val statusCode: Int,
    val data: Any? = null
): RuntimeException()
