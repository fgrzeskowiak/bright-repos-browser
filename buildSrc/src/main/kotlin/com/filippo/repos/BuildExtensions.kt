package com.filippo.repos

import com.android.build.api.dsl.LibraryDefaultConfig
import com.android.build.api.variant.BuildConfigField
import com.android.build.api.variant.LibraryVariant
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

fun BaseExtension.setupJvm() {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

fun KotlinJvmOptions.setupKotlin() {
    jvmTarget = "17"
    freeCompilerArgs = freeCompilerArgs + listOf(
        "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        "-Xopt-in=kotlinx.coroutines.FlowPreview",
        "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi",
        "-Xopt-in=androidx.compose.material3.ExperimentalMaterial3Api",
    )
}

fun BaseExtension.setupDefaultConfig() {
    defaultConfig {
        compileSdkVersion(TARGET_SDK)
        minSdk = MIN_SDK
        targetSdk = TARGET_SDK
    }
}

fun BaseExtension.setupComposeBuildFeatures() {
    buildFeatures.compose = true
    composeOptions.kotlinCompilerExtensionVersion = "1.4.1"
}

fun BaseExtension.enableBuildConfig() {
    buildFeatures.buildConfig = true
}

fun LibraryDefaultConfig.createBuildConfigSecret(name: String, key: String) {
    buildConfigField(
        SECRET_TYPE,
        name,
        SecretsUtil.getStringSecret(key)
    )
}

private const val TARGET_SDK = 33
private const val MIN_SDK = 24
private const val SECRET_TYPE = "String"
const val BASE_NAMESPACE = "com.filippo.repos"
