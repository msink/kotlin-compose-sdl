// SPDX-License-Identifier: Apache-2.0

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://dl.bintray.com/kotlin/kotlin-dev")
    }
}

plugins {
  id("com.gradle.enterprise").version("3.1.1")
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}

include(":collections-immutable")
include(":compose-runtime")
include(":mock-android")
include(":SDL2")
include(":samples:tetris")
