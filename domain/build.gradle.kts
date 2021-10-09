plugins {
    id ("java-library")
    id ("kotlin")
    kotlin ("kapt")
}

dependencies{
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    testImplementation(CommonLibs.junit)

    mockito()

    implementation(Kotlin.stdblib_jdk)
    api(KotlinCoroutine.core)

    koinCore()
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

