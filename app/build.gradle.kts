plugins {
    id ("com.android.application")
    id ("kotlin-android")
    id ("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    
}

android {
    compileSdk =31

    defaultConfig {
        applicationId =("com.example.arivetsamplechallenge")
        minSdk =21
        targetSdk =31
        versionCode =1
        versionName =("1.0")

        testInstrumentationRunner =("androidx.test.runner.AndroidJUnitRunner")
    }
    buildFeatures{
        viewBinding =true
        //dataBinding= true
    }
    buildTypes{
        getByName("debug"){
            isMinifyEnabled=false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget ="1.8"

    }
}

dependencies {
    val     roomVersion = "2.4.0-beta02"
    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.appcompat:appcompat:1.4.0")
    implementation ("com.google.android.material:material:1.4.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.2")
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")

    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:$roomVersion")
    // optional - Test helpers
    testImplementation("androidx.room:room-testing:$roomVersion")
    
    
    implementation ("androidx.paging:paging-runtime-ktx:3.1.0")
    implementation ("androidx.recyclerview:recyclerview:1.3.0-alpha01")
    implementation ("com.github.bumptech.glide:glide:4.12.0")
    kapt ("com.github.bumptech.glide:compiler:4.12.0")

    implementation ("androidx.navigation:navigation-fragment:2.4.0-beta02")
    implementation ("androidx.navigation:navigation-ui:2.4.0-beta02")
    implementation ("androidx.legacy:legacy-support-v4:1.0.0")


    implementation ("com.google.dagger:hilt-android:2.40.5")
    kapt ("com.google.dagger:hilt-compiler:2.40.5")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0") //added for test run code
    implementation ("androidx.fragment:fragment-ktx:1.4.0")
    implementation ("androidx.activity:activity-ktx:1.4.0")

    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.3")

    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0-RC")
    

    testImplementation ("junit:junit:4.+")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
}

