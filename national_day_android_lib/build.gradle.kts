plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("maven-publish")
}

android {
    namespace = "studio.s98.national_day_android_lib"
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
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
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2024.04.01")
    val nav_version = "2.7.7"
    implementation("studio.98s:shared_national_day:1.0.0")


    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation(composeBom)
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.media3:media3-exoplayer:1.4.1")
    implementation("androidx.media3:media3-ui:1.4.1")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("com.airbnb.android:lottie-compose:6.4.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}


val javadocJar = tasks.register("javadocJar", Jar::class.java) {
    archiveClassifier.set("javadoc")
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                from(components["release"])
                groupId = "studio.98s"
                artifactId = "national_day_android_lib"
                version = "1.0.0"
                artifact(javadocJar)
                pom {
                    packaging = "aar"
                    name.set("NationalDayAndroidLib")
                    description.set("NationalDayAndroidLib: A game library for Saudi national day")
                    url.set("https://github.com/SadO-On/98s-saudi-national-day-game-android.git")
                    inceptionYear.set("2024")
                    version = "1.0.0"
                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }
                    issueManagement {
                        system.set("Github")
                        url.set("https://github.com/SadO-On/98s-saudi-national-day-game-android/issues")
                    }
                    developers {
                        developer {
                            id.set("98's Studio")
                            name.set("Mohammed Waleed")
                            email.set("sadondeveloper@gmail.com")
                        }
                    }

                    scm {
                        connection.set("scm:git@github.com:SadO-On/98s-saudi-national-day-game-android")
                        developerConnection.set("scm:git@github.com:SadO-On/98s-saudi-national-day-game-android.git")
                        url.set("https://github.com/SadO-On/98s-saudi-national-day-game-android.git")
                    }
                }
            }
        }
    }
}
