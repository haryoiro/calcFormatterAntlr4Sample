/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Java application project to get you started.
 * For more details on building Java & JVM projects, please refer to https://docs.gradle.org/8.3/userguide/building_java_projects.html in the Gradle documentation.
 */

plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application
    antlr
    java
    id("org.beryx.jlink") version "2.26.0"
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {

    // Use JUnit Jupiter for testing.
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    // guava
    implementation("com.google.guava:guava:32.1.1-jre")

    // csv, properties, toml, yamlに対応
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-toml:2.15.2")

    // cli args
    implementation("info.picocli:picocli:4.7.5")
    annotationProcessor("info.picocli:picocli-codegen:4.7.5")

    // antlr
    antlr("org.antlr:antlr4:4.13.1")

    // tinylog
    implementation("org.tinylog:tinylog-api:2.6.0")
    runtimeOnly("org.tinylog:tinylog-impl:2.6.2")
}

// Apply a specific Java toolchain to ease working on different environments.
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

application {
    mainClass.set("com.haryoiro.calcformat.app.App")
    mainModule.set("com.haryoiro.calcformat")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

jlink {
    options = listOf(
            "--strip-debug", "--compress", "2",
            "--no-header-files",
            "--no-man-pages",
    )

    launcher {
        name = "calcfmt"
    }

    mergedModule {
        additive = true
    }

    forceMerge("jlink","jackson")
}


tasks {
    generateGrammarSource {
        outputDirectory = file("src/main/java/com/haryoiro/calcformat/antlr")
        maxHeapSize = "64m"
        arguments = arguments + listOf("-visitor", "-long-messages")
    }

}