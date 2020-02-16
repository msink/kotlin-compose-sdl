// SPDX-License-Identifier: Apache-2.0

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

    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
        compilations["main"].enableEndorsedLibs = true
        sourceSets["${targetName}Main"].apply {
            kotlin.srcDir("src/nativeMain/kotlin")
            dependencies {
                implementation(project(":SDL2"))
            }
        }
    }
}