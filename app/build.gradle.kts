// build.gradle.kts (nivel de aplicación)
plugins {
    alias(libs.plugins.android.application) // Asegúrate de que el plugin de Android esté habilitado
    id("com.google.gms.google-services") version "4.4.2" apply false // Plugin de Google Services
}

android {
    namespace = "com.lta.test"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.lta.test"
        minSdk = 24
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
}

dependencies {
    implementation(platform("com.google.firebase:firebase-bom:33.5.1")) // Usamos firebase-bom para gestionar las versiones
    implementation("com.google.firebase:firebase-analytics")
    implementation("com.google.firebase:firebase-firestore")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.activity:activity-ktx:1.4.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.espresso:espresso-core:3.4.0")
}

// Al final del archivo build.gradle.kts
apply(plugin = "com.google.gms.google-services")
