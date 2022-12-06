plugins {
    id(Plugins.AndroidApplication)
    id (Plugins.KotlinJB)
    kotlin(Plugins.Kapt)
    id(Plugins.DaggerHilt)
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
        getByName("release") {
            // Enables code shrinking, obfuscation, and optimization for only
            // your project's release build type.
            isMinifyEnabled = true
            // Enables resource shrinking, which is performed by the
            // Android Gradle plugin.
            isShrinkResources = true
            isDebuggable=false
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
    installKtorDependencies()
}
