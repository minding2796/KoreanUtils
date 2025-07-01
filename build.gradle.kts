import com.vanniktech.maven.publish.SonatypeHost

plugins {
    java
    id("com.vanniktech.maven.publish") version "0.31.0" // maven 배포 플러그인
}

group = "io.github.minding2796"
version = "1.0.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

tasks.withType<Test> {
    useJUnitPlatform()
}

mavenPublishing {
    // 이 세가지가 합쳐져서 implementation("io.github.username:my-library:1.0.0") 과 같이 사용됩니다.
    coordinates(
        groupId = "io.github.minding2796", // namespace
        artifactId = "korean-utils", // 배포하려는 library artifact id
        version = "1.0.0" // version
    )

    pom {
        name.set("KoreanUtils") // 라이브러리 이름
        description.set("The Korean Utils") // 라이브러리 설명
        inceptionYear.set("2025") // 라이브러리 시작 년도
        url.set("https://github.com/minding2796/KoreanUtils") // 라이브러리 배포 url

        licenses {
            license { // 라이센스 이름과 라이센스 정보 url 세팅
                name.set("The Apache License, Version 2.0")
                url.set("https://www.apache.org/licenses/LICENSE-2.0.txt")
            }
        }

        developers {
            developer { // 개발자 정보 세팅
                id.set("minding2796")
                name.set("minding2796")
                email.set("minding2796@gmail.com")
            }
        }

        scm { // source code management 정보
            connection.set("scm:git:git://github.com/minding2796/KoreanUtils.git")
            developerConnection.set("scm:git:ssh://github.com/minding2796/KoreanUtils.git")
            url.set("https://github.com/minding2796/KoreanUtils")
        }
    }

    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL) // maven central 배포 위치 설정

    signAllPublications() // 배포 gpg signing 설정
}