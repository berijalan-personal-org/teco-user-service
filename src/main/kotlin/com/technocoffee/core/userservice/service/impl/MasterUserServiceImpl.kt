package com.technocoffee.core.userservice.service.impl

import com.technocoffee.core.userservice.domain.dto.request.ReqLoginDto
import com.technocoffee.core.userservice.domain.dto.request.ReqRegisterDto
import com.technocoffee.core.userservice.domain.dto.request.ReqUpdateUserDto
import com.technocoffee.core.userservice.domain.dto.response.ResLoginDto
import com.technocoffee.core.userservice.domain.dto.response.ResUserDto
import com.technocoffee.core.userservice.domain.entity.MasterUserEntity
import com.technocoffee.core.userservice.exception.CustomException
import com.technocoffee.core.userservice.repository.MasterMembershipRepository
import com.technocoffee.core.userservice.repository.MasterUserRepository
import com.technocoffee.core.userservice.service.MasterUserService
import com.technocoffee.core.userservice.util.BCryptUtil
import com.technocoffee.core.userservice.util.JwtUtil
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class MasterUserServiceImpl (
    private val masterUserRepository: MasterUserRepository,
    private val masterMembershipRepository: MasterMembershipRepository,
    private val bcrypt: BCryptUtil,
    private val jwtUtil: JwtUtil
): MasterUserService  {
    override fun register(req: ReqRegisterDto): ResUserDto {
        val membership = if (req.membershipId == null) {
            Optional.empty()
        } else masterMembershipRepository.findById(req.membershipId)

        if (membership.isEmpty && req.membershipId != null) {
            throw Exception("Membership tidak ditemukan")
        }

        val existingUserEmail = masterUserRepository.findFirstByEmail(req.email)
        if(existingUserEmail != null){
            throw CustomException("Email sudah terdaftar", 400)
        }

        val hashPw = bcrypt.hash(req.password)

        val userRaw = MasterUserEntity(
            email = req.email,
            password = hashPw,
            name = req.name,
            membership = if (membership.isPresent) {
                membership.get()
            } else {
                null
            }
        )

        val user = masterUserRepository.save(userRaw)
        return ResUserDto(
            id = user.id,
            email = user.email,
            name = user.name,
            membershipId = user.membership?.id
        )
    }

    override fun login(req: ReqLoginDto): ResLoginDto {
        val userEntityOpt = masterUserRepository.findFirstByEmail(req.email)

        if (userEntityOpt == null) {
            throw CustomException("Username atau Password salah", 400)
        }

        val userEntity = userEntityOpt

        if (!bcrypt.verify(req.password, userEntity.password)) {
            throw CustomException("Username atau Password salah", 400)
        }

        val membership = if (userEntity.membership != null) {
            userEntity.membership!!.name
        } else {
            "free"
        }

        val token = jwtUtil.generateToken(userEntity.id, membership)

        return ResLoginDto(token)
    }

    @CacheEvict(
        value = ["getUserById"],
        key = "{#userId}"
    )
    override fun updateUser(
        req: ReqUpdateUserDto,
        userId: Int
    ): ResUserDto {
        println("userId $userId")
        val user = masterUserRepository.findById(userId.toInt()).orElseThrow {
            throw CustomException(
                "User id $userId tidak ditemukan",
                HttpStatus.BAD_REQUEST.value()
            )
        }

        val existingUser = masterUserRepository.findFirstByEmail(req.email)
        if(existingUser != null){
            if(existingUser.id != user.id){
                throw CustomException(
                    "Username telah terdaftar",
                    HttpStatus.BAD_REQUEST.value()
                )
            }
        }

        user.email = req.email.toString()
        user.name = req.name.toString()
        user.membership = masterMembershipRepository.findById(req.membershipId?.toInt() ?: 0).orElse(null)

        val result = masterUserRepository.save(user)

        return ResUserDto(
            id = result.id,
            name = result.name,
            email = result.email,
            membershipId = result.membership?.id
        )
    }


    @Cacheable(
        "getUserById",
        key = "{#id}"
    )
    override fun findById(id: Int): ResUserDto {
        val user = masterUserRepository.findById(id).orElseThrow {
            throw CustomException("User dengan ID: ${id} tidak ditemukan!!!", 400)
        }
        return ResUserDto(
            id = user.id,
            email = user.email,
            name = user.name,
            membershipId = user.membership?.id,
        )
    }
}