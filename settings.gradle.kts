enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
rootProject.name = "Gamepdia"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google {
            mavenContent {
                includeGroupAndSubgroups("androidx")
                includeGroupAndSubgroups("com.android")
                includeGroupAndSubgroups("com.google")
            }
        }
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

include(":composeApp")
include(":core_database")
include(":core_network")

include(":search:data")
include(":search:domain")
include(":search:ui")

include(":favorite:data")
include(":favorite:domain")
include(":favorite:ui")


include(":game:data")
include(":game:domain")
include(":game:ui")


include(":common:data")
include(":common:domain")
include(":common:ui")
include(":common:logger")



