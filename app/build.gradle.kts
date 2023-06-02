plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
    alias(libs.plugins.google.services)
    alias(libs.plugins.google.crashlytics)
}

android {
    namespace = "kopycinski.tomasz.klamkify"
    compileSdk = 33

    defaultConfig {
        applicationId = "kopycinski.tomasz.klamkify"
        minSdk = 26
        targetSdk = 33
        versionCode = 5
        versionName = "0.5"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        kapt {
            arguments {
                arg("room.schemaLocation", "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        getByName("release") {
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
        kotlinCompilerExtensionVersion = "1.4.6"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Firebase
    implementation(libs.bundles.firebase)

    // Hilt
    kapt(libs.hilt.compiler)
    implementation(libs.bundles.hilt)

    // Compose
    implementation(libs.bundles.compose)
    androidTestImplementation(libs.compose.ui.testing)
    debugImplementation(libs.bundles.compose.debug)

    // Core
    implementation(libs.androidx.ktx)
    testImplementation(libs.junit4)

    implementation(project(":domain"))
}

kapt {
    correctErrorTypes = true
}