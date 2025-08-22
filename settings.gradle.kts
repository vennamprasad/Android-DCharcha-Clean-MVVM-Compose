pluginManagement {
    repositories {
        // Order matters a bit; keep Google first for AGP
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        // Where your dependencies come from
        google()
        mavenCentral()
    }
}

rootProject.name = "DCharcha"

include(
    ":app",
    ":core:navigation",
    ":core:ui",
    ":core:localization",
    ":core:permissions",
    ":core:common",
    ":core:datastore",
    ":core:database",
    ":core:network",
    ":domain",
    ":feature:splash",
    ":feature:language",
    ":feature:onboarding",
    ":feature:auth",
    ":feature:profile",
    ":feature:home",
)
