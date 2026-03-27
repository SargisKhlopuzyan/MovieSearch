import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.native.coroutines) // this will expose viewmodel in form of state object in iOS side
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

    sourceSets {

        // This is mentioned in kmp.observableviewmodel core GitHub repository
        all {
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
        }

        androidMain.dependencies {
            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.appcompat)
            implementation(libs.androidx.activity.compose)
            //            implementation(project.dependencies.platform(libs.androidx.compose.bom))
        }

        commonMain.dependencies {
            implementation(projects.core.ui)
            implementation(projects.feature.details.domain)

            implementation(libs.kmp.observableviewmodel.core)

            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            implementation(libs.coil)
            implementation(libs.coil.ktor)

            implementation(project.dependencies.platform(libs.androidx.compose.bom))

//            implementation(libs.androidx.compose.material.icons.extended)

            // implementation(libs.jetbrains.compose.ui.tooling)
            implementation(libs.jetbrains.compose.ui.tooling.preview)

            implementation(libs.jetbrains.compose.runtime)
            implementation(libs.jetbrains.compose.foundation)
            implementation(libs.jetbrains.compose.material3)
            implementation(libs.jetbrains.compose.ui)
            implementation(libs.jetbrains.compose.components.resources)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.jetbrains.compose.material.icons.extended)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.junit)
        }
    }
}

android {
    namespace = "com.sargis.moviesearch.feature.details.ui"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

dependencies {
    debugImplementation(libs.jetbrains.compose.ui.tooling)
    //"androidRuntimeClasspath"(libs.androidx.compose.ui.tooling)
}

//compose.desktop {
//    application {
//        mainClass = "com.sargis.moviesearch.MainKt"
//
//        nativeDistributions {
//            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
//            packageName = "com.sargis.moviesearch"
//            packageVersion = "1.0.0"
//        }
//    }
//}