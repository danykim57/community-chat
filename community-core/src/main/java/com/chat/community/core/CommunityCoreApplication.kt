package com.chat.community.core

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class CommunityCoreApplication

fun main(args: Array<String>) {
    runApplication<CommunityCoreApplication>(*args)
}
