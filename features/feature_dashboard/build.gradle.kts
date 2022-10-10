apply {
    from("$rootDir/base_build.gradle")
}

dependencies {
    "api"(project(":services:service_games"))
    "api"(project(":features:core"))
}