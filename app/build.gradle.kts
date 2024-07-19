import com.github.triplet.gradle.androidpublisher.ReleaseStatus

plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    alias(libs.plugins.triplet.play)
    id("kotlin-kapt")
}

android {
    namespace = "com.hillwar.ram"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hillwar.ram"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

play {
    serviceAccountCredentials.set(file("<api>.json"))
}

open class PublishingConfigExtension {
    var track: String = "internal"
    var userFraction: Double = 1.0
    var releaseStatus: ReleaseStatus = ReleaseStatus.COMPLETED
}

val publishingConfig = extensions.create<PublishingConfigExtension>("publishingConfig")

fun PublishingConfigExtension.configure(block: PublishingConfigExtension.() -> Unit) {
    block(this)
}

publishingConfig.configure {
    track = "beta"
    userFraction = 0.5
    releaseStatus = ReleaseStatus.COMPLETED
}

tasks.register("configurePublishing") {
    group = "publishing"
    description = "Configures the publishing settings"

    doLast {
        val trackValue = publishingConfig.track
        val userFractionValue = publishingConfig.userFraction
        val releaseStatusValue = publishingConfig.releaseStatus

        play {
            track.set(trackValue)
            userFraction.set(userFractionValue)
            releaseStatus.set(releaseStatusValue)

        }
    }
}

tasks.register("publishApp") {
    group = "publishing"
    description = "Publishes the app to Google Play"
    dependsOn("configurePublishing", "publishReleaseApk")
}

dependencies {
    implementation(project(":features:main"))

    implementation(libs.coreKtx)
    implementation(libs.appcompat)

    // Dagger-Hilt
    implementation(libs.hiltAndroidV2511)
    androidTestImplementation(libs.androidxUiTestJunit4)
    debugImplementation(libs.androidxUiTooling)
    debugImplementation(libs.androidxUiTestManifest)
    kapt(libs.hiltCompilerV2511)
}
