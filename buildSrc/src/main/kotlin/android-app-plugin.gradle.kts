import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.android.build.gradle.internal.scope.ProjectInfo.Companion.getBaseName
import com.filippo.repos.BASE_NAMESPACE
import com.filippo.repos.setupComposeBuildFeatures
import com.filippo.repos.setupDefaultConfig
import com.filippo.repos.setupJvm
import com.filippo.repos.setupKotlin

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    setupJvm()
    kotlinOptions {
        setupKotlin()
    }
    setupDefaultConfig()
    setupVersion()
    setupBuildTypes()
    setupComposeBuildFeatures()
    namespace = BASE_NAMESPACE
}

fun BaseAppModuleExtension.setupVersion() {
    defaultConfig {
        versionCode = 1
        versionName = "1.0"
    }
}

fun BaseAppModuleExtension.setupBuildTypes() {
    buildTypes {
        getByName("debug") {
            versionNameSuffix = "-DEBUG"
            applicationIdSuffix = ".debug"
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
        }
        getByName("release") {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
}
