package com.technocoffee.core.userservice.controller

import com.technocoffee.core.userservice.domain.constant.Constant
import com.technocoffee.core.userservice.domain.dto.request.ReqLoginDto
import com.technocoffee.core.userservice.domain.dto.request.ReqRegisterDto
import com.technocoffee.core.userservice.domain.dto.request.ReqUpdateUserDto
import com.technocoffee.core.userservice.domain.dto.response.BaseResponse
import com.technocoffee.core.userservice.domain.dto.response.ResLoginDto
import com.technocoffee.core.userservice.domain.dto.response.ResUserDto
import com.technocoffee.core.userservice.service.MasterUserService
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/user")
class UserController (
    private val masterUserService: MasterUserService,
    private val httpServletRequest: HttpServletRequest
) {
    @PostMapping("/register")
    fun register(
        @Valid @RequestBody req: ReqRegisterDto
    ): ResponseEntity<BaseResponse<ResUserDto>> {
        return ResponseEntity(
            BaseResponse(
                data = masterUserService.register(req),
                message = "Register sukses"
            ),
            HttpStatus.CREATED
        )
    }

    @GetMapping("/{id}")
    fun getUserById(
        @PathVariable id: Int
    ): ResponseEntity<BaseResponse<ResUserDto>>{
        return ResponseEntity.ok(
            BaseResponse(
                data = masterUserService.findById(id)
            )
        )
    }

    @PostMapping("/login")
    fun login(
        @RequestBody req: ReqLoginDto
    ): ResponseEntity<BaseResponse<ResLoginDto>> {
        return ResponseEntity(
            BaseResponse(
                data = masterUserService.login(req),
                message = "Login sukses"
            ),
            HttpStatus.OK
        )
    }

    @PutMapping
    fun updateUser(
        @RequestBody req: ReqUpdateUserDto
    ): ResponseEntity<BaseResponse<ResUserDto>>{
        val userId = httpServletRequest.getHeader(Constant.HEADER_USER_ID)
        return ResponseEntity.ok(
            BaseResponse(
                data = masterUserService.updateUser(req, userId.toInt())
            )
        )
    }
}