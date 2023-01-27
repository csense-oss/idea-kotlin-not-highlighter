plugins {
    //https://github.com/JetBrains/gradle-intellij-plugin
    id("org.jetbrains.intellij") version "1.12.0"
    //https://github.com/JetBrains/kotlin
    kotlin("jvm") version "1.8.0"
    kotlin("plugin.serialization") version "1.8.0"
    //https://github.com/jeremylong/dependency-check-gradle/releases
    id("org.owasp.dependencycheck") version "8.0.0"
}

group = "csense-idea"
version = "1.0.4"

intellij {
    updateSinceUntilBuild.set(false)
    plugins.set(listOf("Kotlin", "java"))
    version.set("2021.3.3")
}


repositories {
    mavenCentral()
    mavenLocal()
    maven {
        setUrl("https://pkgs.dev.azure.com/csense-oss/csense-oss/_packaging/csense-oss/maven/v1")
        name = "Csense oss"
    }
}

dependencies {
    implementation("csense.kotlin:csense-kotlin-jvm:0.0.59")
    implementation("csense.kotlin:csense-kotlin-annotations-jvm:0.0.41")
    implementation("csense.kotlin:csense-kotlin-datastructures-algorithms:0.0.41")
    implementation("csense.idea.base:csense-idea-base:0.1.60")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0-RC")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    testImplementation("csense.kotlin:csense-kotlin-tests:0.0.59")
    testImplementation("csense.idea.test:csense-idea-test:0.2.0")
}


tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
    changeNotes.set(
        """
        <ul>
            <li>Fixed various bugs wrt settings</li>
            <li>Built for newer IDEA version</li>
            <li>Added more comprehensive tests</li>
        </ul>
      """
    )
}

tasks.getByName("check").dependsOn("dependencyCheckAnalyze")

tasks {

    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }

    test {
        testLogging {
            showExceptions = true
            showStackTraces = true
            exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        }
    }
}

sourceSets {
    test {
        resources {
            srcDir("testData")
        }
    }
}
