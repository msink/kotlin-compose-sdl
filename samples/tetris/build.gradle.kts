// SPDX-License-Identifier: Apache-2.0

import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

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

    targets.withType<KotlinNativeTarget> {
        compilations["main"].enableEndorsedLibs = true
        sourceSets["${targetName}Main"].apply {
            kotlin.srcDir("src/tetrisMain/kotlin")

            dependencies {
                implementation(project(":SDL2"))
            }
        }

        binaries {
            executable {
                entryPoint = "sample.tetris.main"

                val distTaskName = linkTaskName.replaceFirst("link", "dist")
                val distTask = tasks.register<Copy>(distTaskName) {
                    from("src/tetrisMain/resources")
                    into(linkTask.outputFile.get().parentFile)
                    exclude("*.rc")
                    if (!konanTarget.family.isAppleFamily) {
                        exclude("*.plist")
                    }
                    dependsOn(linkTask)
                }
                tasks["assemble"].dependsOn(distTask)
            }
        }
    }
}
