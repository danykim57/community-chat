package com.chat.community.api

import com.chat.community.api.security.TokenProvider
import com.chat.community.api.config.JwtConfig
import com.chat.community.core.model.user.Email
import com.chat.community.core.model.user.PhoneNumber
import com.chat.community.core.model.user.User
import com.chat.community.core.repository.UserRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.Duration

@SpringBootTest
class TokenFactoryTest @Autowired constructor(
    private val tokenProvider: TokenProvider,
    private val userRepository: UserRepository,
    private val jwtConfig: JwtConfig
) {

    @Test
    fun getSubject() {
    }

    @Test
    fun setSubject() {
    }

    @Test
    fun getIssuedAt() {
    }

    @Test
    fun setIssuedAt() {
    }

    @Test
    fun getExpiresAt() {
    }

    @Test
    fun setExpiresAt() {
    }

    @Test
    fun getClaims() {
    }

    @Test
    fun setClaims() {
    }

    @Test
    fun withDefaultValues() {
    }

    @DisplayName("토근 발급 테스트")
    @Test
    fun generateToken() {
        val key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfig.secret))
        val user = userRepository.save(
            User.builder()
                .name("testUser")
                .email(Email("test123@gmail.com"))
                .phoneNumber(PhoneNumber("0101231234"))
                .password("test")
                .build()
        )

        val token: String = tokenProvider.generateToken(user, Duration.ofDays(14))

        val payload = Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload

        val userId = payload.get("id", Integer::class.java).toLong()

        assertEquals(user.id, userId)
    }
}