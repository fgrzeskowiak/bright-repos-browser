import com.android.build.gradle.internal.scope.ProjectInfo.Companion.getBaseName
import com.filippo.repos.BASE_NAMESPACE
import com.filippo.repos.setupComposeBuildFeatures
import com.filippo.repos.setupDefaultConfig
import com.filippo.repos.setupJvm
import com.filippo.repos.setupKotlin

plugins {
    id("android-library-plugin")
}

android {
    setupComposeBuildFeatures()
}
