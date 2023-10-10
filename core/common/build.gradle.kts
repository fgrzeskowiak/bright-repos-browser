plugins {
    id("android-compose-library-plugin")
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.preview)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
}
