plugins {
	java
	id("org.springframework.boot") version "4.0.4-SNAPSHOT"
	id("io.spring.dependency-management") version "1.1.7"
	id("gg.jte.gradle") version "3.1.16"
}

group = "dev.chilos"
version = "0.0.1-SNAPSHOT"
description = "Personal site"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(25)
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	implementation("gg.jte:jte-spring-boot-starter-3:3.1.16")
	implementation("io.github.wimdeblauwe:htmx-spring-boot:5.0.0")
    implementation("gg.jte:jte-watcher:3.1.16")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

jte {
	generate()
	binaryStaticContent = true
}

tasks.withType<Test> {
	useJUnitPlatform()
}
