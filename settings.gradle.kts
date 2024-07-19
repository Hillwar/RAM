pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "RAM"
include(":app")
include(":core:network")
include(":core:theme")
include(":core:navigation")
include(":core:common")
include(":features:personList")
include(":features:personDetail")
include(":features:error")
include(":features:main")
