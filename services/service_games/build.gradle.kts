apply {
    from("$rootDir/base_build.gradle")
    plugin("jacoco")
}

dependencies {
    "api"(project(":features:core"))
}
