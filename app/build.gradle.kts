plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")

}

android {
    namespace = "com.example.dementiaapp"
    compileSdk = 34
    compileSdkPreview = "UpsideDownCake"

    defaultConfig {
        applicationId = "com.example.dementiaapp"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    viewBinding {
        android.buildFeatures.viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources.excludes += "META-INF/LICENSE"
        resources.excludes += "META-INF/LICENSE-FIREBASE.txt"
        resources.excludes += "META-INF/NOTICE"
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    lint {
        disable += "NotificationPermission"
    }
}


dependencies {

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")

    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("androidx.navigation:navigation-fragment-ktx:2.7.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.1")

    implementation ("de.hdodenhof:circleimageview:3.1.0")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")

    implementation(platform("com.google.firebase:firebase-bom:32.2.3"))

    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation("com.google.firebase:firebase-auth-ktx")

    implementation("com.google.firebase:firebase-database")

    // Also add the dependency for the Google Play services library and specify its version
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    //Progress Dialog library
    implementation ("com.jpardogo.googleprogressbar:library:1.2.0")


    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    implementation("com.google.firebase:firebase-storage-ktx:20.2.1")
    implementation ("com.google.firebase:firebase-firestore-ktx")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation("com.google.android.gms:play-services-maps:18.1.0")

    implementation("com.google.android.material:material:1.9.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.media:media:1.6.0")
    implementation("androidx.core:core-ktx:1.10.1")

    // Google
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:32.2.3"))
    implementation("com.google.firebase:firebase-database-ktx")
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("com.google.firebase:firebase-auth-ktx")

    // Firebase UI
    implementation("com.firebaseui:firebase-ui-auth:8.0.2")
    implementation("com.firebaseui:firebase-ui-database:8.0.2")

    implementation ("com.google.firebase:firebase-firestore")
    implementation ("com.google.firebase:firebase-messaging")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")


}