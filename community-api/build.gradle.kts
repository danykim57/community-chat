plugins {
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
}

dependencies {
    implementation(project(":community-core"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springdoc:springdoc-openapi-data-rest:1.8.0")
    implementation("org.springdoc:springdoc-openapi-ui:1.8.0")
    implementation("org.springframework.security:spring-security-core:6.3.1")
    implementation("io.jsonwebtoken:jjwt-api:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.6")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.6") // or 'io.jsonwebtoken:jjwt-gson:0.12.6' for gson
    /*
      Uncomment this next dependency if you are using:
       - JDK 10 or earlier, and you want to use RSASSA-PSS (PS256, PS384, PS512) signature algorithms.
       - JDK 10 or earlier, and you want to use EdECDH (X25519 or X448) Elliptic Curve Diffie-Hellman encryption.
       - JDK 14 or earlier, and you want to use EdDSA (Ed25519 or Ed448) Elliptic Curve signature algorithms.
      It is unnecessary for these algorithms on JDK 15 or later.
    */
    // runtimeOnly 'org.bouncycastle:bcprov-jdk18on:1.76' // or bcprov-jdk15to18 on JDK 7
    runtimeOnly("org.springdoc:springdoc-openapi-kotlin:1.8.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
