plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "app.freewave.vpn"
    compileSdk = 36
    defaultConfig {
        applicationId = "app.freewave.vpn"
        minSdk = 26
        targetSdk = 35
        versionCode = 9
        versionName = "0.2.2"
        ndk { abiFilters += "arm64-v8a" }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
    buildFeatures { buildConfig = true }
    packaging { jniLibs { useLegacyPackaging = true } }
}
dependencies {
    implementation(files("libs/libbox.aar"))
    testImplementation("junit:junit:4.13.2")
}
