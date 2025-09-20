plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.android)
  id("kotlin-parcelize")
}

android {
  namespace = "com.app"
  compileSdk = 36

  defaultConfig {
    applicationId = "com.app"
    minSdk = 21
    targetSdk = 36
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
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  kotlinOptions {
    jvmTarget = "11"
  }
  buildFeatures {
    viewBinding = true
    buildConfig = true
  }
}

dependencies {
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.material)
  implementation(libs.play.services.location)
  implementation(libs.androidx.swiperefreshlayout)
//  implementation(libs.androidx.recyclerview)
//  implementation(libs.androidx.fragment)
//  implementation(libs.androidx.constraintlayout)
//  implementation(libs.androidx.swiperefreshlayout)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)

  //load image
  implementation("com.github.bumptech.glide:glide:4.15.1")
  implementation("com.squareup.picasso:picasso:2.71828")
  implementation("de.hdodenhof:circleimageview:3.1.0")

  //API
  implementation("com.squareup.retrofit2:retrofit:2.6.1")
  implementation("com.squareup.retrofit2:converter-gson:2.6.1")
  implementation("com.squareup.okhttp3:logging-interceptor:4.9.1")

  //camera
  implementation("androidx.camera:camera-lifecycle:1.3.1")
  implementation("androidx.camera:camera-camera2:1.3.1")
  implementation("androidx.camera:camera-view:1.3.1")
  implementation("androidx.camera:camera-core:1.3.1")

  //chucker
  debugImplementation("com.github.chuckerteam.chucker:library:3.5.2")
}
