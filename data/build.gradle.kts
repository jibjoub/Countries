plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jlleitschuh.gradle.ktlint") version "12.0.3"
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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
}

dependencies {
    implementation(project(mapOf("path" to ":domain")))
    implementation(project(mapOf("path" to ":common")))

    implementation(libs.androidx.ktx)
    implementation(libs.androidx.appcompat)
    // Retrofit
    implementation(libs.retrofit2.retrofit)
    implementation(libs.retrofit2.converter.gson)
    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    // Test
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.mockk:mockk:1.13.7")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}
