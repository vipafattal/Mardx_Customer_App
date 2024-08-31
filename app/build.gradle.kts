import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.serial)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.jetbrains.kotlin.ksp)
    //id("dagger.hilt.android.plugin")
}

android {
    buildFeatures.compose = true

    namespace = "com.mardx.customer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mardx.customer"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        applicationVariants.all {
            val variantName = name
            sourceSets {
                getByName("main") {
                    java.srcDir(File("build/generated/ksp/$variantName/kotlin"))
                }
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_19
        targetCompatibility = JavaVersion.VERSION_19
    }
    kotlin {
        compilerOptions {
            apiVersion.set(KotlinVersion.KOTLIN_2_0)
            jvmTarget.set(JvmTarget.JVM_19)
            languageVersion.set(KotlinVersion.KOTLIN_2_0)
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    ksp {
        arg("KOIN_CONFIG_CHECK", "true")
    }
}



dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.glide)
    implementation(libs.ksp)

    /*Compose */
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)


    implementation(libs.serialization)
    implementation(libs.coroutines)
    implementation(libs.retrofit)
    implementation(libs.retrofit.serial)
    implementation(libs.okhttp.intercptor)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.compose.lifecycle.viewmodel)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.lifecycle.runtime.android)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.viewmodel)
    implementation(libs.koin.compose)

    implementation(platform(libs.koin.annotations.bom))
    implementation(libs.koin.ksp.core)
    implementation(libs.koin.ksp.annotations)

    testImplementation(libs.junit)
    testImplementation(libs.tests.mockwebserver)
    testImplementation(libs.tests.arch)
    testImplementation(libs.tests.coroutines)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //implementation(libs.google.hilt)
    //ksp(libs.google.hilt.compiler)


}