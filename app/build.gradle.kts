import org.jlleitschuh.gradle.ktlint.reporter.ReporterType

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.example.icasei"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.icasei"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "YOUTUBE_API_KEY", "\"AIzaSyCr5rHiuc_4g4CsAnXK_eQj4VHFb6K8iNo\"")
        buildConfigField("String", "BASE_URL", "\"https://www.googleapis.com/youtube/v3/\"")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
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
        compose = true
        buildConfig = true
    }
}

tasks.getByPath("preBuild").dependsOn("ktlintFormat")

ktlint {
    android = true
    ignoreFailures = false
    version = "1.4.0"
    reporters {
        reporter(ReporterType.PLAIN)
        reporter(ReporterType.CHECKSTYLE)
        reporter(ReporterType.SARIF)
    }
    filter {
        exclude("**/generated/**")
        include("**/kotlin/**")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // screen
    implementation(libs.coil)
    implementation(libs.paging)
    implementation(libs.paging.compose)

    // player
    implementation(libs.youtube.player)

    // requests
    implementation(libs.squareup.logging.interceptor)
    implementation(libs.moshi)
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter)

    // Hilt
    implementation(libs.androidx.hilt)
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    // Room
    implementation(libs.androidx.room)
    implementation(libs.androidx.room.paging)
    ksp(libs.androidx.room.compiler)
}
