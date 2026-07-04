plugins {
    java
}

dependencies {
    implementation(project(":core"))
    implementation(libs.bundles.logging)
    testImplementation(libs.junit.jupiter)
}