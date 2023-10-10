plugins {
    id("android-app-plugin")
}

dependencies {
    implementation(project(":feature:details"))
    implementation(project(":feature:search"))

    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.core)
    implementation(libs.androidx.activity.compose)
    implementation(libs.bundles.compose)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
}
