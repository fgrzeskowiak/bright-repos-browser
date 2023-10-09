plugins {
    id("android-app-plugin")
}

android {
    namespace = "com.filippo.repos"
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.core)
    implementation(libs.androidx.activity.compose)
    implementation(libs.bundles.compose)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
}
