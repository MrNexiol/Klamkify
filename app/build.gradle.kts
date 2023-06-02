plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    id("com.google.firebase.crashlytics")
    id("com.google.gms.google-services")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.android")
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
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