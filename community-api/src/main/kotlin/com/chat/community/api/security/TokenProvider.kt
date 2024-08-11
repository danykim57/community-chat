package com.chat.community.api.security

import com.chat.community.api.config.JwtConfig
import com.chat.community.core.model.user.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service
import java.util.Date
import javax.crypto.SecretKey

@Service
class TokenProvider(private val jwtConfig: JwtConfig) {

    private val key: SecretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfig.secret))

    fun generateToken(user: User, expiredAt: java.time.Duration): String {
        val now = Date()
        return createTokenWithProperties(Date(now.time + expiredAt.toMillis()), user)
    }

    private fun createTokenWithProperties(expiry: Date, user: User): String {
        val now = Date()
        return Jwts.builder()
            .header().type("JWT").and()
            .issuer(jwtConfig.issuer)
            .issuedAt(now)
            .expiration(expiry)
            .subject(user.email.address)
            .claim("id", user.id)
            .signWith(key) //Default is HS256 with 32 bytes long key
            .compact()
    }

    fun validateToken(token: String?): Boolean {
        try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    fun getAuthentication(token: String): Authentication {
        val claims = getClaims(token)

        val authorities: Set<SimpleGrantedAuthority> =
            setOf(SimpleGrantedAuthority("ROLE_USER"))

        return UsernamePasswordAuthenticationToken(
            org.springframework.security.core.userdetails.User(
                claims.subject,
                "",
                authorities
            ), token, authorities
        )
    }

    fun getUserId(token: String): Long {
        val claims = getClaims(token)
        return claims.get("id", Long::class.java)
    }

    private fun getClaims(token: String): Claims {
        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .payload
    }
}