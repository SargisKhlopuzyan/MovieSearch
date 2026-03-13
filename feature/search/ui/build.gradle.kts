import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
//    alias(libs.plugins.kotlinx.serialization)
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

    sourceSets {

        // This is mentioned in kmp.observableviewmodel core GitHub repository
        all {
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
            languageSettings.optIn("kotlinx.cinterop.ExperimentalForeignApi")
        }

        androidMain.dependencies {
            implementation(libs.coil)
            implementation(libs.coil.ktor)

            implementation(libs.androidx.core.ktx)
            implementation(libs.androidx.appcompat)
            implementation(libs.material)
            implementation(libs.androidx.activity.compose)
            implementation(project.dependencies.platform(libs.androidx.compose.bom))
            implementation(libs.compose.ui)
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.material3)
            implementation(libs.compose.material)
            implementation(libs.compose.runtime)
        }

        commonMain.dependencies {
            implementation(projects.feature.search.domain)
            implementation(libs.kmp.observableviewmodel.core)

            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)
            implementation(libs.junit)
        }
    }
}

android {
    namespace = "com.sargis.bookpedia.feature.search.ui"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

//compose.desktop {
//    application {
//        mainClass = "com.sargis.bookpedia.MainKt"
//
//        nativeDistributions {
//            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
//            packageName = "com.sargis.bookpedia"
//            packageVersion = "1.0.0"
//        }
//    }
//}