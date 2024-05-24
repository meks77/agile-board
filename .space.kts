/**
 * JetBrains Space Automation
 * This Kotlin script file lets you automate build activities
 * For more info, see https://www.jetbrains.com/help/space/automation.html
 */

job("Test backend") {
    container(displayName = "Run gradlew from another dir", image = "amazoncorretto:21-alpine") {
        workDir = "backend"

        kotlinScript { api ->
            api.gradlew("test")
        }
    }
}

