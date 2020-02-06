// SPDX-License-Identifier: Apache-2.0

import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
}

kotlin {
    val isRunningInIde: Boolean = System.getProperty("idea.active") == "true"
    val host = org.gradle.internal.os.OperatingSystem.current()!!
    if (host.isLinux) {
        linuxX64()
        if (!isRunningInIde) {
            linuxArm32Hfp()
        }
    }
    if (host.isMacOsX) {
        macosX64()
    }
    if (host.isWindows) {
        mingwX64()
        if (!isRunningInIde) {
            mingwX86()
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        commonTest {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
    }

    targets.withType<KotlinNativeTarget> {
        sourceSets["${targetName}Main"].apply {
            kotlin.srcDir("src/nativeMain/kotlin")
        }
    }
}
