// SPDX-License-Identifier: Apache-2.0

plugins {
    kotlin("multiplatform") version "1.4.0-dev-1693-105" apply false
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://dl.bintray.com/kotlin/kotlin-dev")
    }
}
