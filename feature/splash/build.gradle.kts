plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.dcharcha.feature.splash"
    compileSdk = 36
    defaultConfig { minSdk = 24 }

    buildFeatures { compose = true }

    // Kotlin 2.x + Compose plugin -> no manual composeOptions needed
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
}

dependencies {
    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material3)
    implementation(libs.navigation.compose)
    implementation(libs.hilt.navigation.compose)

    // Feature contracts & shared UI
    implementation(project(":core:navigation"))
    implementation(project(":core:ui"))

    // Lottie for the splash animation
    implementation(libs.lottie.compose)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    ksp(libs.hilt.compiler.ext)
}
