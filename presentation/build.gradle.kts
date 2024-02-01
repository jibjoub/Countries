plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)
}

android {
    namespace = "com.example.presentation"
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
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
    val lifecycleVersion = "2.6.2"
    val livedataVersion = "1.5.4"
    val coilVersion = "2.0.0"
    val composeBomVersion = "2023.03.00"

    implementation(project(":domain"))
    implementation(project(":common"))

    implementation(libs.androidx.ktx)
    implementation(libs.androidx.appcompat)
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
    implementation("androidx.compose.runtime:runtime-livedata:$livedataVersion")
    // Compose
    implementation(platform("androidx.compose:compose-bom:$composeBomVersion"))
    implementation(libs.activity.compose)
    implementation(libs.compose.ui.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    // Async images
    implementation("io.coil-kt:coil-compose:$coilVersion")
    implementation("io.coil-kt:coil-svg:$coilVersion")
    // DI
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    // Test
    testImplementation(libs.junit)
}
