import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.NativeBuildType

plugins {
    kotlin("multiplatform")
}

kotlin {
    val os = org.gradle.internal.os.OperatingSystem.current()!!
    if (os.isLinux) linuxX64("tetris") {
        binaries {
            executable {
                entryPoint = "sample.tetris.main"
                linkerOpts("-L/usr/lib64", "-L/usr/lib/x86_64-linux-gnu", "-lSDL2")
            }
        }
    }

    if (os.isMacOsX) macosX64("tetris") {
        binaries {
            executable {
                entryPoint = "sample.tetris.main"
                linkerOpts("-L/opt/local/lib", "-L/usr/local/lib", "-lSDL2")
            }
        }
    }

    if (os.isWindows) mingwX64("tetris") {
        binaries {
            executable {
                entryPoint = "sample.tetris.main"
                linkerOpts(
                        "-Wl,-Bstatic",
                        "-lstdc++",
                        "-static",
                        "-limm32",
                        "-lole32",
                        "-loleaut32",
                        "-lversion",
                        "-lwinmm",
                        "-lsetupapi",
                        "-mwindows"
                    )
            }
        }
    }

    targets.withType<KotlinNativeTarget> {
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
