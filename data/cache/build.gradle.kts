plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
    id("com.squareup.sqldelight")
}

android {
    compileSdk = BuildConfig.compileSdk

    defaultConfig {
        compileSdk = BuildConfig.compileSdk

        defaultConfig {
            minSdk = BuildConfig.minSdk
            targetSdk = BuildConfig.targetSdk

            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
            consumerProguardFiles("consumer-rules.pro")

            multiDexEnabled = true
        }

        buildTypes {
            debug {
                isMinifyEnabled = false
            }

            getByName("release") {
                isMinifyEnabled = true
                proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
            }

        }
        compileOptions {
            isCoreLibraryDesugaringEnabled = true
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
        }
        kotlinOptions {
            jvmTarget = "11"
        }
    }

}

sqldelight {
    database("CountriesDb") {
        packageName = "com.wayyan.countriessqldelight.data.cache.db"
        sourceFolders = listOf("sqldelight")

        schemaOutputDirectory = file("src/main/sqldelight/schemas")
        verifyMigrations = true
    }
}

dependencies {
    implementation(project(":data:common"))
    implementation(project(":domain"))

    coreLibraryDesugaring(CommonLibs.desugar_lib)
    implementation(CommonLibs.timber)

    implementation(Kotlin.stdblib_jdk)
    implementation(AndroidXCore.core_ktx)

    moshi()

    //Database
    implementation(AndroidXSqlite.sqlite_ktx)
    implementation(SqlDelight.android_driver)
    implementation(SqlDelight.coroutine)

    //koin
    koinAndroid()

    //Testing
    // testImplementation(project(":coroutinetestrule"))
    testImplementation(CommonLibs.junit)
    testImplementation("app.cash.turbine:turbine:0.2.0")
    mockito()
    mockitoAndroid()
    androidXTest()
}