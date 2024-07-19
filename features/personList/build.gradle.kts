plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.hilt)
    id("kotlin-kapt")
}

android {
    namespace = "com.hillwar.ram.features.personList"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
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
            excludes += setOf("/META-INF/AL2.0", "/META-INF/LGPL2.1")
        }
    }
}

dependencies {
    implementation(project(":core:network"))
    implementation(project(":core:theme"))
    implementation(project(":core:navigation"))
    implementation(project(":core:common"))
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
    implementation(libs.uiTestJunit4Android)

    // Test dependencies
    androidTestImplementation(libs.androidxTestExtJunit)
    androidTestImplementation(libs.espressoCore)
    androidTestImplementation(libs.androidxUiTestJunit4)
    androidTestImplementation(libs.androidxUiTestManifest)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.core)
    androidTestImplementation(libs.rules)

    // Coil
    implementation(libs.coilComposeV222)

    // Dagger - Hilt
    implementation(libs.hiltAndroidV246)
    kapt(libs.hiltCompiler)
    implementation(libs.hiltNavigationCompose)
    implementation(libs.androidxPaletteKtx)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.loggingInterceptor)
    implementation(libs.converterMoshi)

    // ktx
    implementation(libs.fragmentKtxV155)
    implementation(libs.lifecycleViewmodelKtxV260)
    implementation(libs.lifecycleLivedataKtxV260)
    implementation(libs.androidxLifecycleExtensions)

    testImplementation(libs.junit)
    testImplementation(libs.coreTesting)
    testImplementation(libs.kotlinxCoroutinesTest)
    testImplementation(libs.mockk)
    testImplementation(libs.mockitoCore)

    implementation(platform(libs.composeBom))

    // Compose dependencies
    implementation(libs.androidxUi)
    implementation(libs.androidxUiToolingPreview)
    implementation(libs.androidxMaterial)

    // UI Testing dependencies
    androidTestImplementation(libs.androidxUiTestJunit4)
    debugImplementation(libs.androidxUiTooling)
    androidTestImplementation (libs.hiltAndroidTesting)
    kaptAndroidTest (libs.hiltAndroidCompiler)
}
