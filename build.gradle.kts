import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.diffplug.spotless") version "5.6.1"
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}

buildscript {
    repositories {
        mavenCentral()
        google()
        maven(url = "https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:7.0.4")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.4.0")
        classpath("com.google.gms:google-services:4.3.10")
    }
}

subprojects {
    apply{ plugin("com.diffplug.spotless")}
    configure<com.diffplug.gradle.spotless.SpotlessExtension>  {
        kotlin {
            target("**/*.kt")
            ktlint("0.39.0").userData(mapOf(
                "disabled_rules" to "no-wildcard-imports")
            )
        }
    }
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions.allWarningsAsErrors = true
        kotlinOptions.freeCompilerArgs = listOf(
            "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }
}