// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.gms.google-services") version "4.3.15" apply false
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin") version "2.0.1" apply false
    id("com.android.library") version "8.1.1" apply false
}



buildscript {
    dependencies {
        classpath ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }

}

tasks {
    register("clean", Delete::class) {
        delete(rootProject.buildDir)
    }
}

