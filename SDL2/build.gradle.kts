// SPDX-License-Identifier: Apache-2.0

import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
}

kotlin {
    val os = org.gradle.internal.os.OperatingSystem.current()!!

    if (os.isLinux) linuxX64("linux") {
        compilations["main"].apply {
            cinterops.create("sdl") {
                includeDirs("/usr/include/SDL2")
            }
            kotlinOptions.freeCompilerArgs = listOf(
                "-include-binary", "/usr/lib/x86_64-linux-gnu/libSDL2.a"
            )
        }
    }

    if (os.isMacOsX) macosX64("macosx") {
        compilations["main"].apply {
            cinterops.create("sdl") {
                includeDirs("/opt/local/include/SDL2", "/usr/local/include/SDL2")
            }
            kotlinOptions.freeCompilerArgs = listOf(
                "-include-binary", "/usr/local/lib/libSDL2.a"
            )
        }
    }

    if (os.isWindows) mingwX64("windows") {
        val mingwPath = File(
            System.getenv("RUNNER_TEMP")?.let { it + "/msys/msys64/mingw64" } ?:
            System.getenv("MINGW64_DIR") ?: "C:/msys64/mingw64")
        compilations["main"].apply {
            cinterops.create("sdl") {
                includeDirs(mingwPath.resolve("include/SDL2"))
            }
            kotlinOptions.freeCompilerArgs = listOf(
                "-include-binary", mingwPath.resolve("lib/libSDL2.a").toString()
            )
        }
    }
}
