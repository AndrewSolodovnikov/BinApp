plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.safeargs)
    alias(libs.plugins.serialization)
    id("kotlin-parcelize")
}

android {
    namespace = "com.sol.binapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.sol.binapp"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.dynamic.features.fragment)
    androidTestImplementation(libs.androidx.navigation.testing)

    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)
    implementation (libs.retrofit)
    implementation(libs.retrofit2.kotlinx.serialization.converter)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
}
