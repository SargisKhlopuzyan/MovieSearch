import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    val xcf = XCFramework()

    listOf(
        iosArm64(),
        iosSimulatorArm64(),
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"

            export(project(":core:network"))

            export(project(":feature:search:data"))
            export(project(":feature:search:domain"))
            export(project(":feature:search:ui"))

            export(project(":feature:details:data"))
            export(project(":feature:details:domain"))
            export(project(":feature:details:ui"))

            isStatic = false
            xcf.add(this)
        }
    }

    jvm()
//    jvm("desktop")

    sourceSets {

        androidMain.dependencies {
//            implementation(projects.feature.search.ui)
        }

        commonMain.dependencies {
            // api
            implementation(projects.core.network)

            implementation(projects.feature.search.data)
            implementation(projects.feature.search.domain)
            api(projects.feature.search.ui)

            implementation(projects.feature.details.data)
            implementation(projects.feature.details.domain)
            api(projects.feature.details.ui)
            // - end of api

            implementation(libs.koin.core)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.junit)
        }
    }
}

android {
    namespace = "com.sargis.moviesearch.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}