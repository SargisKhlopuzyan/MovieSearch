import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    iosArm64()
    iosSimulatorArm64()

    jvm()
    //    jvm("desktop")

//    targets.withType<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget> {
//        binaries.all {
//            freeCompilerArgs += "-Xdisable-phases=TestRunner" // disable tests
//        }
//    }

    sourceSets {
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
        }
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.serialization)

            implementation(libs.koin.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.junit)
        }
//        val desktopMain by getting {
//            dependencies {
//                implementation(libs.ktor.client.desktop)
//            }
//        }
        jvmMain.dependencies {
//            implementation(libs.kotlinx.coroutinesSwing)
            implementation(libs.ktor.client.desktop)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
        }
    }
}

android {
    namespace = "com.sargis.moviesearch.core.network"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}