package com.chat.community.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication


@SpringBootApplication(scanBasePackages = ["com.chat.community"])
@ConfigurationPropertiesScan
class CommunityApiApplication

fun main(args: Array<String>) {
    runApplication<CommunityApiApplication>(*args)
}