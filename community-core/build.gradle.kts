plugins {
    java
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    api("org.springframework.data:spring-data-jpa:3.3.2")
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.apache.commons:commons-lang3:3.15.0")
    implementation("com.google.guava:guava:33.2.1-jre")
    implementation("org.springframework.security:spring-security-core:6.3.1")
    compileOnly("org.projectlombok:lombok")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}