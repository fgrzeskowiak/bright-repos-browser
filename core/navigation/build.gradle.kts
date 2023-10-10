plugins {
    id("android-library-plugin")
}

dependencies {
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
    implementation(libs.compose.navigation)
}
