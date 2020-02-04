// SPDX-License-Identifier: Apache-2.0

import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
}

kotlin {
    val os = org.gradle.internal.os.OperatingSystem.current()!!
    if (os.isLinux) linuxX64("linux")
    if (os.isMacOsX) macosX64("macosx")
    if (os.isWindows) mingwX64("windows")

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        commonTest {
            kotlin.srcDir("src/unitTest/kotlin")
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }

    targets.withType<KotlinNativeTarget> {
        sourceSets["${targetName}Main"].apply {
            kotlin.srcDir("src/nativeMain/kotlin")
            kotlin.srcDir("src/libuiMain/kotlin")
            dependencies {
                implementation(project(":collections-immutable"))
            }
        }
    }
}
