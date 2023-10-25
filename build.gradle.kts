import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

object Version {
    const val kotest = "5.5.5"
}

extra["kotlin-coroutines.version"] = "1.6.4"

plugins {
    id("org.springframework.boot") version "3.0.1"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.8.0"
    kotlin("plugin.spring") version "1.8.0"
    kotlin("kapt") version "1.8.0"  //java annotation processing 시스템을 사용하기 위한
    kotlin("plugin.noarg") version "1.8.10"
    //kotlin("plugin.jpa") version "1.4.32" // JPA를 사용하기 위한 플러그인
    kotlin("plugin.serialization") version "1.5.31" // kotlin serialization 사용을 위한 플러그인
    idea
}

group = "pe.kotlin.mongo"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_18

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

noArg {
    annotation("pe.kotlin.mongo.support.extension.NoArg")
    invokeInitializers = true
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8") // jdk8 이하 호환되는 표준라이브러리 제공을 위한

    //implementation("org.springframework.boot:spring-boot-starter-data-jpa") // JPA를 사용하기 위한 의존성
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    implementation("io.micrometer:micrometer-registry-prometheus")

    //mapstruct
    implementation("org.mapstruct:mapstruct:1.5.3.Final")
    kapt("org.mapstruct:mapstruct-processor:1.5.3.Final")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")

    //kotest
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test")
    testImplementation("io.kotest:kotest-runner-junit5:${Version.kotest}")
    testImplementation("io.kotest:kotest-assertions-core:${Version.kotest}")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "18"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
