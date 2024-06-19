plugins {
    kotlin("jvm") version "1.9.23"
}

group = "dev.xaaf"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("net.dv8tion:JDA:5.0.0-beta.24")
    implementation("ch.qos.logback:logback-classic:1.5.6")

    implementation("org.xerial:sqlite-jdbc:3.41.2.2")

    implementation("io.github.cdimascio:dotenv-kotlin:6.2.2")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}