/**
 * JetBrains Space Automation
 * This Kotlin script file lets you automate build activities
 * For more info, see https://www.jetbrains.com/help/space/automation.html
 */

job("Build backend") {
    container(displayName = "Run gradlew from another dir", image = "amazoncorretto:17-alpine") {
        workDir = "backend"

        kotlinScript { api ->
            api.gradlew("build")
        }
    }
}

job("Test backend") {
    container(displayName = "Run gradlew from another dir", image = "amazoncorretto:17-alpine") {
        workDir = "backend"

        kotlinScript { api ->
            api.gradlew("test")
        }
    }
}

