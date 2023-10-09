package com.filippo.repos

import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.gradle.kotlin.dsl.configure

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
        "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi"
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

private const val TARGET_SDK = 33
private const val MIN_SDK = 24
