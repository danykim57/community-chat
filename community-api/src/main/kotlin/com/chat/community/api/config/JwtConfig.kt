package com.chat.community.api.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "jwt")
data class JwtConfig @ConstructorBinding constructor(
    val issuer: String,
    val secret: String
)
