package com.chat.community.api.controller

import com.chat.community.api.dto.UserLogin
import com.chat.community.api.service.UserService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(val userService: UserService) {

    @Operation(summary = "run the user login feature", description = "Return 200 for the success and 401 for authentication fail")
    @PostMapping
    fun login(@RequestBody requestDto: UserLogin): ResponseEntity<String> {
        val result:String = userService.login(requestDto)
        return ResponseEntity.ok(result);
    }
}