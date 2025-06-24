package com.technocoffee.core.userservice.service

import com.technocoffee.core.userservice.domain.dto.request.ReqLoginDto
import com.technocoffee.core.userservice.domain.dto.request.ReqRegisterDto
import com.technocoffee.core.userservice.domain.dto.request.ReqUpdateUserDto
import com.technocoffee.core.userservice.domain.dto.response.ResLoginDto
import com.technocoffee.core.userservice.domain.dto.response.ResUserDto


interface MasterUserService {
    fun register(req: ReqRegisterDto): ResUserDto
    fun login(req: ReqLoginDto): ResLoginDto
    fun updateUser(req: ReqUpdateUserDto, userId: Int): ResUserDto
    fun findById(id: Int): ResUserDto
}