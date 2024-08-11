package com.chat.community.api.dto

import com.chat.community.core.model.user.Email

data class UserLogin(
    val email: Email,
    val password: String,
)
