plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.spin_bottle_app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.spin_bottle_app"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
    }
    buildToolsVersion = rootProject.extra["buildToolsVersion"] as String

}

dependencies {

    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.core.ktx.v190)
    implementation(libs.androidx.appcompat.v161)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.androidx.ui.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //navigation
    implementation(libs.androidx.navigation.fragment.ktx.v235)
    implementation(libs.androidx.navigation.ui.ktx.v235)
    implementation(libs.androidx.navigation.common)

    //cardView
    implementation(libs.androidx.cardview)

    //RecyclerView
    implementation(libs.androidx.recyclerview)

    //corrutinas
    implementation(libs.kotlinx.coroutines.android)

    // viewmodel
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)

    // LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    implementation(libs.floatingactionbutton)

    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //Glide
    implementation(libs.glide)

    //Lottie
    implementation(libs.lottie)

    androidTestImplementation(libs.androidx.espresso.core.v351)

    //Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)

    implementation(libs.firebase.firestore)
    implementation(libs.firebase.auth.ktx)

    //DaggerHilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
}