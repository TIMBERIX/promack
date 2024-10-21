plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    application
}

application {
    mainClass = "toys.timberix.promack.MainKt"
}

group = "toys.timberix"
version = "0.0.1"

repositories {
    mavenCentral()
    mavenLocal()
}

@Suppress("PropertyName")
val ktor_version = "3.0.0"
dependencies {
    implementation("io.ktor:ktor-client-core-jvm:3.0.0")
    implementation("io.ktor:ktor-client-apache:3.0.0")
    testImplementation(kotlin("test"))

    // NO SLF4J
    implementation("org.slf4j:slf4j-nop:2.0.13")

    // Kotlinx.serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.1")

    // Kotlinx.coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0-RC.2")

    // Kotlinx.datetime
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.1")

    // Prometheus Metrics
    implementation("io.prometheus:prometheus-metrics-core:1.3.1")
    implementation("io.prometheus:prometheus-metrics-exporter-httpserver:1.3.1")

    // Ktor client
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
    compilerOptions {
        freeCompilerArgs = listOf("-Xcontext-receivers")
    }
}
