rootProject.name = "community"
include("community-api", "community-push", "community-core")

pluginManagement {
    repositories {
        mavenCentral()
    }
}