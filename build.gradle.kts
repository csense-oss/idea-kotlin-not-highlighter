plugins {
    //https://github.com/JetBrains/gradle-intellij-plugin
    id("org.jetbrains.intellij") version "1.17.3"
    //https://github.com/JetBrains/kotlin
    kotlin("jvm") version "1.9.23"
    //https://github.com/Kotlin/kotlinx.serialization
    kotlin("plugin.serialization") version "1.9.23"
    //https://jeremylong.github.io/DependencyCheck/
    id("org.owasp.dependencycheck") version "9.1.0"
}

val javaVersion = "11"

group = "csense-idea"
version = "2.1.0"

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
    //https://github.com/csense-oss/csense-kotlin
    implementation("csense.kotlin:csense-kotlin-jvm:0.0.60")
    //https://github.com/csense-oss/csense-kotlin-annotations
    implementation("csense.kotlin:csense-kotlin-annotations-jvm:0.0.63")
    //https://github.com/csense-oss/csense-kotlin-datastructures-algorithms
    implementation("csense.kotlin:csense-kotlin-datastructures-algorithms:0.0.41")
    //https://github.com/csense-oss/idea-kotlin-shared-base
    implementation("csense.idea.base:csense-idea-base:0.1.63")
    //https://github.com/Kotlin/kotlinx.serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    //https://github.com/Kotlin/kotlinx.coroutines
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
    //https://github.com/csense-oss/csense-kotlin-test
    testImplementation("csense.kotlin:csense-kotlin-tests:0.0.60")
    //https://github.com/csense-oss/csense-oss-idea-kotlin-shared-test
    testImplementation("csense.idea.test:csense-idea-test:0.3.0")
}


tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
    changeNotes.set(
        """
            Now able to change colors for either operators and functions and comments
            And a custom list of words to highlight 
      """
    )
    sinceBuild.set("213")
}

tasks.getByName("check").dependsOn("dependencyCheckAnalyze")

tasks {

    withType<JavaCompile> {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    compileKotlin {
        kotlinOptions.jvmTarget = javaVersion
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = javaVersion
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