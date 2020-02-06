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
        linuxX64("tetris")
    }
    if (host.isMacOsX) {
        macosX64("tetris")
    }
    if (host.isWindows) {
        mingwX64("tetris")
        if (!isRunningInIde) {
            mingwX86()
        }
    }

    targets.withType<KotlinNativeTarget> {
        binaries {
            executable {
                entryPoint = "sample.tetris.main"
            }
        }

        sourceSets["${targetName}Main"].apply {
            dependencies {
                implementation(project(":SDL2"))
            }
        }

        compilations["main"].enableEndorsedLibs = true
    }
}

afterEvaluate {
    val tetris: KotlinNativeTarget by kotlin.targets
    val linkTasks = NativeBuildType.values().mapNotNull { tetris.binaries.getExecutable(it).linkTask }

    linkTasks.forEach { linkTask ->
        linkTask.doLast {
            copy {
                from(kotlin.sourceSets["tetrisMain"].resources)
                into(linkTask.outputFile.get().parentFile)
                exclude("*.rc")
            }
        }
    }
}
