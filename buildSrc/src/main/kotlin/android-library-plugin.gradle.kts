import com.android.build.gradle.internal.scope.ProjectInfo.Companion.getBaseName
import com.filippo.repos.BASE_NAMESPACE
import com.filippo.repos.setupDefaultConfig
import com.filippo.repos.setupJvm
import com.filippo.repos.setupKotlin

plugins {
    id("com.android.library")
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
    namespace = "$BASE_NAMESPACE.${project.getBaseName().get()}"
}
