package com.chat.community.api.security

import com.chat.community.api.config.JwtConfig
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import java.util.*

class TokenFactory {
    var subject: String = "danielKim135@gmail.com";
    var issuedAt: Date = Date()
    var expiresAt: Date = Date()
    var claims: Map<String, Any> = Collections.emptyMap()

    fun withDefaultValues(): TokenFactory {
        return TokenFactory();
    }

    fun generateToken(jwtConfig: JwtConfig): String {
        return Jwts.builder()
            .header().type("JWT").and()
            .issuer(jwtConfig.issuer)
            .issuedAt(issuedAt)
            .expiration(expiresAt)
            .subject(subject)
            .claims(claims)
            .signWith(Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfig.secret))) //Default is HS256 with 32 bytes long key
            .compact()
    }
}