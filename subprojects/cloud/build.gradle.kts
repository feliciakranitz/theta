plugins {
	id("org.springframework.boot") version "2.2.7.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	id("java-common")
	application
}

group = "hu.bme.mit.theta"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
}

application {
	mainClassName = "hu.bme.mit.theta.cloud.CloudApplication"
}

dependencies {

	implementation("org.springframework.boot:spring-boot-starter-amqp")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	compile(group = "io.swagger.core.v3", name =  "swagger-annotations", version= "2.0.3")
	compile (group= "io.springfox", name= "springfox-swagger2", version= "2.7.0")
	compile (group= "io.springfox", name= "springfox-swagger-ui", version= "2.7.0")
	compile (group= "com.rabbitmq", name= "amqp-client", version= "5.9.0")
	compile (group= "io.dropwizard", name= "dropwizard-core", version= "2.0.10")
//	developmentOnly "org.springframework.boot:spring-boot-devtools"
	runtimeOnly("mysql:mysql-connector-java")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.amqp:spring-rabbit-test")


	compile(project(":theta-cfa"))
	compile(project(":theta-cfa-analysis"))
	compile(project(":theta-solver-z3"))
	compile(project(":theta-sts"))
	compile(project(":theta-sts-analysis"))
	compile(project(":theta-xta"))
	compile(project(":theta-xta-analysis"))
}


tasks.withType<Test> {
	useJUnitPlatform()
}
