plugins {
    id("java")
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("plugin.jpa") version "1.9.24"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}

allprojects {
    group = "com.chat"
    version = "0.0.1-SNAPSHOT"
    repositories {
        mavenCentral()
    }
}
subprojects {

    apply {
        plugin("java")
        plugin("org.jetbrains.kotlin.jvm")
    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }

    kotlin {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
        }
    }

    tasks {
        compileKotlin {
            kotlinOptions.jvmTarget = "17"
            kotlinOptions.freeCompilerArgs = listOf(
                "-Xopt-in=kotlin.RequiresOptIn",
                "-XXLanguage:+InlineClasses"
            )
        }

        compileTestKotlin {
            kotlinOptions.jvmTarget = "17"
        }
    }

    tasks.register("prepareKotlinBuildScriptModel"){}

    tasks.test {
        useJUnitPlatform()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}
