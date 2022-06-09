import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id ("java")
    id("io.quarkus")
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.allopen") version "1.6.21"
    kotlin("kapt") version "1.6.21"
}

repositories {
//    maven { url "https://maven.aliyun.com/repository/public/" }
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))
    implementation("io.quarkus:quarkus-smallrye-openapi")
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
    implementation("io.quarkus:quarkus-resteasy-reactive-qute")
    implementation("io.quarkus:quarkus-rest-client-reactive-jackson")

    implementation("io.quarkus:quarkus-qute")
    implementation("io.quarkus:quarkus-smallrye-jwt")
    implementation("io.quarkus:quarkus-smallrye-jwt-build")
    implementation("io.quarkus:quarkus-smallrye-health")

    implementation("io.quarkus:quarkus-kubernetes")
    implementation("io.quarkus:quarkus-container-image-jib")

    // db
    implementation("io.quarkus:quarkus-flyway")
    implementation("io.quarkus:quarkus-hibernate-envers")
    implementation("io.quarkus:quarkus-hibernate-orm-panache")
    implementation("io.quarkus:quarkus-hibernate-validator")
    implementation("io.quarkus:quarkus-jdbc-postgresql")

    // redis
    implementation("io.quarkus:quarkus-redis-client")

    implementation("io.quarkus:quarkus-smallrye-graphql")
    implementation("org.eclipse.microprofile.metrics:microprofile-metrics-api")
//    implementation("io.quarkus:quarkus-smallrye-graphql-client")

    // other
    implementation("io.quarkus:quarkus-scheduler")
    implementation("io.quarkus:quarkus-quartz")
    implementation("io.quarkus:quarkus-smallrye-reactive-messaging")
//    implementation("io.quarkus:quarkus-elasticsearch-rest-high-level-client")
//    implementation("io.quarkus:quarkus-hibernate-search-orm-elasticsearch")

    implementation ("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation ("org.projectlombok:lombok:1.18.24")
    implementation("org.mapstruct:mapstruct:1.4.2.Final")
    implementation("com.querydsl:querydsl-jpa:5.0.0")
    kapt("com.querydsl:querydsl-apt:5.0.0:jpa")
    kapt("org.projectlombok:lombok:1.18.24")
    kapt("org.mapstruct:mapstruct-processor:1.4.2.Final")

    implementation("io.quarkus:quarkus-mailer")
    implementation("io.quarkus:quarkus-cache")
    implementation("io.quarkus:quarkus-vertx")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.smallrye.reactive:smallrye-mutiny-vertx-web-client")
    implementation("org.apache.commons:commons-lang3:3.12.0")

    implementation("io.quarkus:quarkus-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.quarkus:quarkus-panache-mock")
    testImplementation("io.rest-assured:rest-assured")
    testImplementation("io.quarkus:quarkus-test-security-jwt")
}

group "io.github.zhengchalei"
version "1.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    kotlinOptions.javaParameters = true
}

//compileJava {
//    options.encoding = "UTF-8"
//    options.compilerArgs << "-parameters"
//}
//
//compileTestJava {
//    options.encoding = "UTF-8"
//}
