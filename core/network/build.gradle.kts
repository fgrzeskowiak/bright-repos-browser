import com.filippo.repos.createBuildConfigSecret
import com.filippo.repos.enableBuildConfig

plugins {
    id("android-library-plugin")
}

android {
    enableBuildConfig()
    defaultConfig {
        createBuildConfigSecret(name = "BASE_URL", key = "baseUrl")
    }
}

dependencies {
    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.arrow)
    implementation(libs.bundles.network)
}
