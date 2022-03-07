import java.util.*


plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.example.the_name_quiz_app"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner ="androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packagingOptions {
        resources.excludes.add("META-INF/*")
    }

    lint {
        textReport = true
        lintConfig = rootProject.file("lint.xml")
        isCheckDependencies = true
        isCheckTestSources = true
        isExplainIssues = true
        isCheckReleaseBuilds = true
        isAbortOnError = true
        disable("UnusedIds", "StaticFieldLeak", "UnusedResources", "ObsoleteLintCustomCheck")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions.jvmTarget = "11"

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.6.10")
    implementation("androidx.core:core-splashscreen:1.0.0-beta01")


    // Logging
    implementation("com.jakewharton.timber:timber:5.0.1")

    // Room
    val roomVersion = "2.4.2"
    implementation("androidx.room:room-runtime:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

    // Koin
    val koinVersion = "3.1.2"
    implementation("io.insert-koin:koin-android:$koinVersion")
    implementation("io.insert-koin:koin-core:$koinVersion")

    // Coroutines
    val coroutinesVersion = "1.5.0"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")


    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    testImplementation("junit:junit:4.13.2")

    // ViewModel
    val lifecycleVersion = "2.4.0-rc01"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")


    // Navigation
    val navigationVersion = "2.3.5"
    implementation("androidx.navigation:navigation-common-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-runtime-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")
    androidTestImplementation("androidx.navigation:navigation-testing:$navigationVersion")
    debugImplementation("androidx.fragment:fragment-testing:1.4.1")

    // Gson
    implementation("com.google.code.gson:gson:2.8.8")

    // Data binding for ViewPager and RecyclerView
    implementation("me.tatarka.bindingcollectionadapter2:bindingcollectionadapter-recyclerview:4.0.0")
    implementation("me.tatarka.bindingcollectionadapter2:bindingcollectionadapter-viewpager2:4.0.0")

    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test:rules:1.4.0")
    androidTestImplementation("org.mockito:mockito-android:2.24.5")
    androidTestImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.2")
}