import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	java
	kotlin("jvm") version "1.6.20-M1"
	id("org.springframework.boot") version("2.6.3")
	id("io.spring.dependency-management") version("1.0.11.RELEASE")
	id("io.freefair.lombok") version("6.4.0")
	id("com.github.johnrengelman.processes") version("0.5.0")  //needed for springdoc
	id("org.springdoc.openapi-gradle-plugin") version("1.3.0") //
}

group = "com.circleescape"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_11
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa") {
		exclude(group= "org.hibernate", module= "hibernate-core")
	}
	implementation("org.hibernate:hibernate-core:5.6.5.Final")
	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group= "org.junit.vintage", module= "junit-vintage-engine")
	}
	implementation( "org.springdoc:springdoc-openapi-ui:1.6.6")
	implementation("org.apache.commons:commons-math3:3.6.1")
	implementation("javax.validation:validation-api")
	annotationProcessor("io.swagger:swagger-annotations:1.6.5")
	implementation("io.swagger:swagger-annotations:1.6.5")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("com.h2database:h2:2.1.210")
	testImplementation("io.rest-assured:rest-assured:4.5.1")
	testImplementation("io.rest-assured:json-path:4.5.1")
	testImplementation("io.rest-assured:xml-path:4.5.1")
	implementation(kotlin("stdlib-jdk8"))

}

tasks {
    test {
	    useJUnitPlatform()
    }
}

openApi {
    apiDocsUrl.set("http://localhost:8080/v3/api-docs.yaml")
    outputDir.set(file("$buildDir/docs"))
    outputFileName.set("myopenapi.yaml")
    waitTimeInSeconds.set(10)
    forkProperties.set("-Dspring.profiles.active=special")
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
	jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
	jvmTarget = "1.8"
}