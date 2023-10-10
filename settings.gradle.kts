@file:Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "bright-repos-browser"

include(
    ":app",
    ":core:database",
    ":core:network",
    ":feature:details",
    ":feature:search",
)
