/*
 * Copyright 2019 Google LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

plugins {
    id("com.diffplug.gradle.spotless") version "3.26.1"
}

buildscript {

    Config.run { repositories.deps() }

    dependencies {
        classpath("com.android.tools.build:gradle:${Versions.gradle_plugin}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        classpath("com.google.gms:google-services:${Versions.googleServices}")
        classpath("io.fabric.tools:gradle:${Versions.fabric}")
    }
}

spotless {
    kotlin {
        target("**/*.kt")
        ktlint("0.36.0")
        licenseHeaderFile("$rootDir/scripts/copyright.txt", "(package |import |@file:)")
    }
    kotlinGradle {
        // same as kotlin, but for .gradle.kts files (defaults to '*.gradle.kts')
        target("*.gradle.kts")

        ktlint("0.36.0")

        licenseHeaderFile("$rootDir/scripts/copyright.txt", "(plugins |import |include)")
    }
}
subprojects {
    Config.run {
        repositories.deps()
    }
}