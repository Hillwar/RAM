plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    id("kotlin-kapt")
}

android {
    namespace = "com.hillwar.ram.core.network"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.material)

    // Dagger-Hilt
    implementation(libs.hiltAndroidV2511)
    kapt(libs.hiltCompilerV2511)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.converterMoshi)

    implementation(libs.converterGson)
    implementation(libs.gson)

    // Logging interceptor for OkHttpClient
    implementation(libs.loggingInterceptor)
}
