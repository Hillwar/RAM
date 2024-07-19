plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    id("kotlin-kapt")
}

android {
    namespace = "com.hillwar.ram.features.personDetail"
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

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:theme"))
    implementation(project(":core:common"))
    implementation(project(":core:navigation"))
    implementation(project(":features:error"))

    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.lifecycleRuntimeKtx)
    implementation(libs.activityCompose)
    implementation(libs.androidxUi)
    implementation(libs.androidxUiToolingPreview)
    implementation(libs.androidxMaterial)
    implementation(libs.androidxNavigationCompose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidxTestExtJunit)
    androidTestImplementation(libs.espressoCore)

    // Coil
    implementation(libs.coil)

    // Dagger - Hilt
    implementation(libs.hiltAndroidV2511)
    kapt(libs.hiltCompilerV2511)
    kapt(libs.androidxHiltCompiler)
    implementation(libs.hiltNavigationCompose)
    implementation(libs.androidxPaletteKtx)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.okhttpV500alpha3)
    implementation(libs.loggingInterceptor)
    implementation(libs.converterMoshi)

    // ktx
    implementation(libs.androidxFragmentKtx)
    implementation(libs.androidxLifecycleViewmodelKtx)
    implementation(libs.androidxLifecycleLivedataKtx)
    implementation(libs.androidxLifecycleExtensions)
}