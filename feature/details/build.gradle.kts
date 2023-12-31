plugins {
    id("android-compose-library-plugin")
    id("org.jetbrains.kotlin.plugin.serialization")
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":core:navigation"))

    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.retrofit)
    implementation(libs.kotlinx.serialization)
    implementation(libs.arrow)
    implementation(libs.bundles.compose)

    testImplementation(libs.bundles.tests)
}
