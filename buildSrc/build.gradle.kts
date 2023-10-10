plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation(gradleKotlinDsl())
    implementation(libs.android.gradle.plugin)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.hilt.gradle.plugin)
    implementation(libs.kotlinx.serialization.gradle.plugin)
}
