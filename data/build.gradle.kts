plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.android.kotlin)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
}

android {
    namespace = "kopycinski.tomasz.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 26

        consumerProguardFiles("consumer-rules.pro")

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
    kapt {
        arguments {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
}

dependencies {

    // Hilt
    kapt(libs.hilt.compiler)
    implementation(libs.bundles.hilt)

    // Room
    kapt(libs.room.compiler)
    implementation(libs.bundles.room)
    testImplementation(libs.room.testing)
    annotationProcessor(libs.room.compiler)

    // Core
    implementation(libs.androidx.ktx)
    testImplementation(libs.junit4)
}