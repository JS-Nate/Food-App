plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.foodapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.foodapp"
        minSdk = 24
        targetSdk = 33
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
}


// old
dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.squareup.picasso:picasso:2.8")
    implementation ("com.getkeepsafe.taptargetview:taptargetview:1.13.3")
    implementation ("com.google.android.gms:play-services-maps:17.0.1")
    implementation ("com.github.bumptech.glide:glide:4.12.0")



    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0") {
            because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
        }
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0") {
            because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
        }
    }
}


// new
//dependencies {
//    implementation("androidx.appcompat:appcompat:1.6.1")
//    implementation("com.google.android.material:material:1.10.0")
//    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
//    testImplementation("junit:junit:4.13.2")
//    androidTestImplementation("androidx.test.ext:junit:1.1.5")
//    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//    implementation("androidx.exifinterface:exifinterface:1.3.3") // Replace with AndroidX equivalent
//    implementation("com.squareup.picasso:picasso:2.71828")
//
//    constraints {
//        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0") {
//            because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
//        }
//        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0") {
//            because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
//        }
//    }
//}
//






















