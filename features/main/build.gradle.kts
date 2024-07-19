plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    id("kotlin-kapt")
}

android {
    namespace = "com.hillwar.ram.features.main"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

dependencies {
    implementation(project(":core:theme"))
    implementation(project(":features:error"))
    implementation(project(":core:navigation"))
    implementation(project(":features:personList"))
    implementation(project(":features:personDetail"))

    implementation(libs.coreKtx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.navigationUiKtx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidxTestExtJunit)
    androidTestImplementation(libs.espressoCore)

    // Compose
    implementation(libs.androidxNavigationCompose)
    implementation(libs.activityCompose)
    implementation(platform(libs.composeBom))
    implementation(libs.androidxUi)
    implementation(libs.uiGraphics)
    implementation(libs.androidxUiToolingPreview)
    implementation(libs.androidxComposeMaterial3Material3)

    // Coroutines
    implementation(libs.kotlinxCoroutinesCore)
    implementation(libs.kotlinxCoroutinesAndroid)

    // Coroutine Lifecycle Scopes
    implementation(libs.androidxLifecycleViewmodelKtx)
    implementation(libs.lifecycleRuntimeKtx)

    // Coil
    implementation(libs.coil)

    // Dagger-Hilt
    implementation(libs.hilt)
    androidTestImplementation(platform(libs.composeBom))
    androidTestImplementation(libs.androidxUiTestJunit4)
    debugImplementation(libs.androidxUiTooling)
    debugImplementation(libs.androidxUiTestManifest)
    kapt(libs.hiltCompilerV2511)

    // ktx
    implementation(libs.androidxFragmentKtx)
    implementation(libs.androidxLifecycleViewmodelKtx)
    implementation(libs.androidxLifecycleLivedataKtx)
    implementation(libs.androidxLifecycleExtensions)
}