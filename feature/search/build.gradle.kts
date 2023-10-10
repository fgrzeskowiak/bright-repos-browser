plugins {
    id("android-compose-library-plugin")
}

dependencies {
    implementation(platform(libs.compose.bom))
    implementation(project(":core:database"))
    implementation(project(":core:navigation"))

    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.bundles.compose)
    implementation(libs.arrow)
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    testImplementation(libs.bundles.tests)
}
