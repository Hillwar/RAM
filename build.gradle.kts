buildscript {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.triplet.play) apply false
}

tasks.register("publishToPlayStore") {
    group = "publishing"
    description = "Publishes the app to Google Play"

    dependsOn(":app:publishApp")
}
