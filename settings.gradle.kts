// settings.gradle.kts

pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)  // Esto asegura que los repositorios se definan solo en este archivo
    repositories {
        google()
        mavenCentral()
    }
}

// Incluir módulos del proyecto (en este caso, ':app' es el módulo principal)
include(":app")
