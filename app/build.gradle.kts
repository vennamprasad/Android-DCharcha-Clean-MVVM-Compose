plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.dcharcha.android"
    compileSdk = 36
    defaultConfig {
        applicationId = "com.dcharcha.android"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        vectorDrawables { useSupportLibrary = true }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            manifestPlaceholders["buildTypeName"] = ""
        }
        debug {
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            manifestPlaceholders["buildTypeName"] = "Debug"
        }

    }


    flavorDimensions += listOf("brand", "env")

    productFlavors {
        // --- Brand flavors ---
        create("google") {
            dimension = "brand"
            applicationId = "com.dcharcha.google"
            manifestPlaceholders["brandName"] = "Google"
        }
        create("amazon") {
            dimension = "brand"
            applicationId = "com.dcharcha.amazon"
            manifestPlaceholders["brandName"] = "Amazon"
        }

        // --- Environment flavors ---
        create("prod") {
            dimension = "env"
            manifestPlaceholders["envName"] = ""
        }
        create("beta") {
            dimension = "env"
            applicationIdSuffix = ".beta"
            versionNameSuffix = "-beta"
            manifestPlaceholders["envName"] = "Beta"
        }
        create("stag") {
            dimension = "env"
            applicationIdSuffix = ".stag"
            versionNameSuffix = "-stag"
            manifestPlaceholders["envName"] = "Stag"
        }
    }

    // optional: configure signingConfigs and flavor signing mapping
    signingConfigs {
        create("releaseKey") {
            // avoid hardcoding: load from gradle.properties or CI secrets
            storeFile = file("../keystore/release.jks")
            storePassword = project.findProperty("keystore.storePassword") as String?
            keyAlias = project.findProperty("keystore.keyAlias") as String?
            keyPassword = project.findProperty("keystore.keyPassword") as String?
        }
    }

    // map signing per flavor+buildType if needed
    applicationVariants.all {
        val variant = this
        if (variant.buildType.name == "release") {
            // choose signing based on brand/env if desired
            signingConfigs.getByName("releaseKey")
        }

        val brand = variant.productFlavors.find { it.dimension == "brand" }?.name ?: ""
        val env = variant.productFlavors.find { it.dimension == "env" }?.name ?: ""
        val brandName = variant.mergedFlavor.manifestPlaceholders["brandName"] ?: ""
        val envName = variant.mergedFlavor.manifestPlaceholders["envName"] ?: ""
        val buildTypeName = variant.buildType.manifestPlaceholders["buildTypeName"] ?: ""

        val parts = listOf(brandName, envName, buildTypeName)
        val finalName = parts.joinToString(" ")

        variant.mergedFlavor.manifestPlaceholders["appLabel"] = finalName

        println("✅ Variant ${variant.name} → appLabel = $finalName")
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    packaging {
        resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" }
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
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.foundation)
    implementation(libs.compose.material3)
    implementation(libs.material)
    implementation(libs.activity.compose)
    implementation(libs.navigation.compose)

    implementation(libs.hilt.android)
    implementation(libs.material3.window.size.class1.android)
    ksp(libs.hilt.android.compiler)
    ksp(libs.hilt.compiler.ext)
    implementation(libs.hilt.navigation.compose)

    implementation(libs.splashscreen)
    implementation(libs.lottie.compose)
    implementation(libs.paging.compose)

    implementation(project(":core:navigation"))
    implementation(project(":core:ui"))
    implementation(project(":core:localization"))
    implementation(project(":core:permissions"))
    implementation(project(":core:common"))
    implementation(project(":core:datastore"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":feature:splash"))
    implementation(project(":feature:language"))
    implementation(project(":feature:onboarding"))
    implementation(project(":feature:auth"))
    implementation(project(":feature:profile"))
    implementation(project(":feature:home"))
}


tasks.register("createFlavorSources") {
    group = "setup"
    description =
        "Creates source sets for brand/env flavors with placeholder strings.xml & icons"

    doLast {
        val flavors = listOf("google", "amazon")
        val envs = listOf("prod", "beta", "stag")

        val baseDir = project.projectDir.resolve("src")
        val brandingDir = project.projectDir.resolve("branding/icons")

        // brand-only
        flavors.forEach { brand ->
            val valuesDir = baseDir.resolve("$brand/res/values").apply { mkdirs() }
            val stringsFile = valuesDir.resolve("strings.xml")
            if (!stringsFile.exists()) {
                stringsFile.writeText(
                    """<resources>
    <string name="app_name">DCharcha ${brand.replaceFirstChar { it.uppercase() }}</string>
</resources>"""
                )
            }

            // copy icons
            val mipmapDir =
                baseDir.resolve("$brand/res/mipmap-anydpi-v26").apply { mkdirs() }
            val adaptiveFile = mipmapDir.resolve("ic_launcher.xml")
            if (!adaptiveFile.exists()) {
                adaptiveFile.writeText(
                    """<?xml version="1.0" encoding="utf-8"?>
<adaptive-icon xmlns:android="http://schemas.android.com/apk/res/android">
    <background android:drawable="@mipmap/ic_launcher_background"/>
    <foreground android:drawable="@mipmap/ic_launcher_foreground"/>
</adaptive-icon>"""
                )
            }

            // Copy PNGs into mipmap-xxxdpi
            listOf("mdpi", "hdpi", "xhdpi", "xxhdpi", "xxxhdpi").forEach { dpi ->
                val mipmapSub = baseDir.resolve("$brand/res/mipmap-$dpi").apply { mkdirs() }
                val srcDir = brandingDir.resolve(brand)
                val foreground = srcDir.resolve("ic_launcher_foreground.png")
                val background = srcDir.resolve("ic_launcher_background.png")
                if (foreground.exists()) foreground.copyTo(
                    mipmapSub.resolve("ic_launcher_foreground.png"),
                    overwrite = true
                )
                if (background.exists()) background.copyTo(
                    mipmapSub.resolve("ic_launcher_background.png"),
                    overwrite = true
                )
            }
        }

        // env-only
        envs.forEach { env ->
            val valuesDir = baseDir.resolve("$env/res/values").apply { mkdirs() }
            val stringsFile = valuesDir.resolve("strings.xml")
            if (!stringsFile.exists()) {
                stringsFile.writeText(
                    """<resources>
    <string name="env_name">${env.uppercase()}</string>
</resources>"""
                )
            }
        }

        // brand+env combos (optional overrides)
        flavors.forEach { brand ->
            envs.forEach { env ->
                val comboDir =
                    baseDir.resolve("${brand}${env.replaceFirstChar { it.uppercase() }}/res/values")
                        .apply { mkdirs() }
                val stringsFile = comboDir.resolve("strings.xml")
                if (!stringsFile.exists()) {
                    stringsFile.writeText(
                        """<resources>
    <string name="app_name">DCharcha ${brand.replaceFirstChar { it.uppercase() }} ${env.uppercase()}</string>
</resources>"""
                    )
                }
            }
        }

        println("Flavor folders, strings, and icons created/copied successfully.")
    }
}
