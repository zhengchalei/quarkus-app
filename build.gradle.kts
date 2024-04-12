plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.allopen") version "1.9.23"
    kotlin("kapt") version "1.9.23"
    id("io.quarkus")
}

repositories {
    maven {
        url = uri("https://maven.aliyun.com/repository/public/")
    }
    mavenCentral()
    mavenLocal()
}

var group = "io.github.zhengchalei"
var version = "1.0"

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))

    implementation("io.quarkus:quarkus-smallrye-openapi")
    implementation("io.quarkus:quarkus-resteasy-jackson")
    implementation("io.quarkus:quarkus-smallrye-graphql")

    implementation("io.quarkus:quarkus-qute")
    implementation("io.quarkus:quarkus-smallrye-jwt")
    implementation("io.quarkus:quarkus-smallrye-jwt-build")

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

    // other
    implementation("io.quarkus:quarkus-smallrye-reactive-messaging")

    kapt("com.querydsl:querydsl-apt:5.1.0:jakarta")
    implementation("com.querydsl:querydsl-jpa:5.1.0:jakarta")

    implementation("io.quarkus:quarkus-cache")
    implementation("io.quarkus:quarkus-vertx")
    implementation("io.quarkus:quarkus-arc")

    implementation("io.quarkus:quarkus-kotlin")

    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.quarkus:quarkus-panache-mock")
    testImplementation("io.rest-assured:rest-assured")
    testImplementation("io.quarkus:quarkus-test-security-jwt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}


tasks.withType<Test> {
    systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
}

allOpen {
    annotation("jakarta.ws.rs.Path")
    annotation("jakarta.enterprise.context.ApplicationScoped")
    annotation("jakarta.persistence.Entity")
    annotation("io.quarkus.test.junit.QuarkusTest")
}


tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_21.toString()
    kotlinOptions.javaParameters = true
}

