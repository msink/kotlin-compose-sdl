// SPDX-License-Identifier: Apache-2.0

plugins {
    kotlin("multiplatform") version "1.3.70-eap-184" apply false
}

allprojects {
    repositories {
        mavenCentral()
        maven("https://dl.bintray.com/kotlin/kotlin-dev")
    }
}
