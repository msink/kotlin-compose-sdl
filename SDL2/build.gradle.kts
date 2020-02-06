// SPDX-License-Identifier: Apache-2.0

plugins {
    kotlin("multiplatform")
}

kotlin {
    val isRunningInIde: Boolean = System.getProperty("idea.active") == "true"
    val host = org.gradle.internal.os.OperatingSystem.current()!!

    if (host.isLinux) {
        linuxX64 {
            compilations["main"].apply {
                cinterops.create("sdl") {
                    includeDirs("/usr/include/SDL2")
                }
            }
        }
        if (!isRunningInIde) {
            linuxArm32Hfp {
                val konanDir = System.getenv("KONAN_DATA_DIR")?.let { File(it) }
                    ?: File(System.getProperty("user.home")).resolve(".konan")
                compilations["main"].apply {
                    cinterops.create("sdl") {
                        includeDirs(konanDir.resolve("dependencies/target-sysroot-2-raspberrypi/usr/include/SDL2"))
                    }
                }
            }
        }
    }

    if (host.isMacOsX) {
        macosX64 {
            compilations["main"].apply {
                cinterops.create("sdl") {
                    includeDirs("/opt/local/include/SDL2", "/usr/local/include/SDL2")
                }
            }
        }
    }

    if (host.isWindows) {
        mingwX64 {
            val mingwPath = File(
                System.getenv("RUNNER_TEMP")?.let { "$it/msys/msys64/mingw64" } ?:
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
        if (!isRunningInIde) {
            mingwX86 {
                val mingwPath = File(
                    System.getenv("RUNNER_TEMP")?.let { "$it/msys/msys64/mingw32" } ?:
                    System.getenv("MINGW32_DIR") ?: "C:/msys64/mingw32")
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
    }
}
