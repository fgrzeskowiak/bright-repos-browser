plugins {
    id("android-library-plugin")
}

dependencies {
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.bundles.room)
    kapt(libs.room.compiler)
}
