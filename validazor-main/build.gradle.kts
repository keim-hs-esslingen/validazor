// Provided by gradle.properties in this module.
val metaName: String by project
val metaGroup: String by project
val metaArtifactId: String by project
val metaVersion: String by project
val metaDescription: String by project
val jupiterApiVersion: String by project

// Provided by gradle.properties in gradle home dir.
val mavenUsername: String by project
val mavenPassword: String by project

plugins {
    kotlin("jvm")
    `java-library`
    `maven-publish`
    signing
}

group = metaGroup
description = metaDescription
version = metaVersion

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":validazor-core"))
    implementation(project(":validazor-common-constraints"))
    implementation(project(":validazor-jakarta"))

    testImplementation(project(":validazor-test-utils"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jupiterApiVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }

            pom {
                artifactId = metaArtifactId
                description.set(metaDescription)
                url.set("https://github.com/keim-hs-esslingen/validazor")

                licenses {
                    license {
                        name.set("Mozilla Public License Version 2.0")
                        url.set("https://github.com/keim-hs-esslingen/validazor/raw/main/LICENSE")
                    }
                }

                developers {
                    developer {
                        id.set("boesch")
                        name.set("Ben Oesch")
                        email.set("ben.oesch@hs-esslingen.de")
                    }
                }

                scm {
                    connection.set("scm:git:https://github.com/keim-hs-esslingen/validazor.git")
                    developerConnection.set("scm:git:https://github.com/keim-hs-esslingen/validazor.git")
                    url.set("https://github.com/keim-hs-esslingen/validazor")
                }
            }
        }
    }

    repositories {
        maven {
            val releasesRepoUrl = "https://oss.sonatype.org/service/local/staging/deploy/maven2/"
            val snapshotsRepoUrl = "https://oss.sonatype.org/content/repositories/snapshots"
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
            credentials {
                username = mavenUsername
                password = mavenPassword
            }
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications["mavenJava"])
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}