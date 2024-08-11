package com.chat.community.api.service

import com.chat.community.api.dto.UserLogin
import com.chat.community.core.repository.UserRepository
import com.chat.community.api.security.TokenProvider
import org.springframework.stereotype.Service
import java.time.Duration

@Service
class UserService(
    private val userRepository: UserRepository,
    private val tokenProvider: TokenProvider
) {

    fun login(userLogin: UserLogin): String {
        val account = userRepository.findByEmail(userLogin.email).get()
        return tokenProvider.generateToken(account, Duration.ofDays(14))
    }
}