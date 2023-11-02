plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    namespace = "com.demo.capsule"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.demo.capsule"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        viewBinding = true
        dataBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Android.coreKtx)
    implementation(Android.lifecycleKtx)
    implementation(Android.activityCompose)
    implementation(Android.appcompat)
    implementation(Android.material)
    implementation(Android.activityKtx)
    implementation(Android.fragmentKtx)
    implementation(Android.multiDex)
    implementation(Android.constraintLayout)

    implementation(Dependencies.magicalExoPlayer)

    implementation(Lifecycle.viewModel)
    implementation(Lifecycle.liveData)
    implementation(Lifecycle.runtimeKtx)

    implementation(Coroutines.core)
    implementation(Coroutines.android)

    implementation(Moshi.moshi)
    implementation(Moshi.codeGen)
    kapt(Moshi.codeGen)

    implementation(Retrofit.retrofit)
    implementation(Retrofit.interceptor)
    implementation(Retrofit.moshiRetrofitConverter)

    implementation(Hilt.hiltAndroid)
    kapt(Hilt.hiltCompiler)
    kapt(Hilt.hiltInfoCompiler)

    implementation(platform(Compose.composeBom))
    implementation(Compose.composeUi)
    implementation(Compose.composeUiGraphics)
    implementation(Compose.composeUiPreview)
    implementation(Compose.material3)
    testImplementation(Testing.jUnit)
    androidTestImplementation(Testing.extJUnit)
    androidTestImplementation(Testing.espresso)
    androidTestImplementation(platform(Compose.composeBom))
    androidTestImplementation(Testing.junit4)
    debugImplementation(Compose.uiTooling)
    debugImplementation(Compose.manifest)
}