import java.io.FileInputStream
import java.util.*

plugins {
    id(Plugins.AndroidApplication)
    id (Plugins.KotlinJB)
    kotlin(Plugins.Kapt)
    id(Plugins.DaggerHilt)
    kotlin(Plugins.KTXSerialization) version "1.7.20"
}

android {
    namespace = "com.mediaapps.movierepo"
    compileSdk = AppConfig.compileSDK

    defaultConfig {
        applicationId = AppConfig.AppID
        minSdk = AppConfig.minSDK
        targetSdk = AppConfig.targetVersion
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        val prop = Properties().apply {
            try {
                load(FileInputStream(File(rootProject.rootDir, "env.properties")))
            } catch (th : Throwable) {
                th.printStackTrace()
                this.setProperty("API_KEY","\"\"")
            }
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable=false
            buildConfigField("String","API_KEY",prop.getProperty("API_KEY"))
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug"){
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable=true
            buildConfigField("String","API_KEY", prop.getProperty("API_KEY"))
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.1"
    }
    kapt {
        correctErrorTypes = true
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    installComposeDependencies()
    installKotlinEssentialsDependencies()
    installHiltDependencies()
    installMediaDependencies()
    installKtorDependencies()
}


