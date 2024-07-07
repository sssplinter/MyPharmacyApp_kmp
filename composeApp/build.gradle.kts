import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.sqldelight)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = false
        }
    }

    val sqldelightVersion = "2.0.0"

    sourceSets {
        val desktopMain by getting

        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)

                @OptIn(ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation(libs.compose.material.dialogs.datetime)
                implementation(libs.listenablefuture)
                implementation(libs.kotlinx.datetime)

                implementation("cafe.adriel.voyager:voyager-navigator:1.0.0-rc10") {
                    exclude(
                        group = "androidx.lifecycle",
                        module = "lifecycle-viewmodel-ktx"
                    )
                }

                implementation(libs.androidx.lifecycle.viewmodel)
                implementation("app.cash.sqldelight:runtime:$sqldelightVersion")
            }
        }

        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation("androidx.activity:activity-compose:1.9.0") {
                exclude(
                    group = "androidx.lifecycle",
                    module = "lifecycle-viewmodel-ktx"
                )
            }

            implementation("app.cash.sqldelight:android-driver:$sqldelightVersion")

        }

        iosMain.dependencies {
            implementation("app.cash.sqldelight:native-driver:$sqldelightVersion")
        }

        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation("app.cash.sqldelight:sqlite-driver:$sqldelightVersion")
            implementation(libs.kotlinx.coroutines.swing)
            implementation(libs.sqlite.jdbc)
        }
    }
}

android {
    namespace = "kr.sementsova.kmp_compose"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "kr.sementsova.kmp_compose"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

dependencies {
    implementation(libs.androidx.compiler)
    implementation(libs.androidx.core.ktx)
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "kr.sementsova.kmp_compose"
            packageVersion = "1.0.0"
        }
    }
}

sqldelight {
    databases {
        create("MyPharmacyDatabase") {
            packageName.set("kr.sementsova.composeapp.db")
        }
    }
}
