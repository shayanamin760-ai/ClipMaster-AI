plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "com.clipmaster.ai"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.clipmaster.ai"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        // Replace with your deployed HTTPS backend URL before building.
        buildConfigField("String", "APP_URL", "\"https://YOUR-CLIPMASTER-SERVER.example\"")
    }
    buildFeatures { buildConfig = true }
}
