import org.gradle.kotlin.dsl.DependencyHandlerScope





fun DependencyHandlerScope.installKotlinEssentialsDependencies() {
    Dependencies.AndroidKotlin.run {
        dependencies.add(
            "implementation",
            Core
        )
        dependencies.add(
            "implementation",
            ViewModel
        )
        dependencies.add(
            "implementation",
            DataStore
        )
        dependencies.add(
            "implementation",
        Coroutines
        )
        dependencies.add(
            "implementation",
            LifeCycleRuntime
        )
        dependencies.add(
            "testImplementation",
            Junit4
        )
        dependencies.add(
            "testImplementation",
            TextExt
        )
        dependencies.add(
            "androidTestImplementation",
            EspressoTest
        )
    }
}


fun DependencyHandlerScope.installHiltDependencies() {
    Dependencies.Hilt.run {
        dependencies.add(
            "implementation",
            Hilt
        )

        dependencies.add(
            "kapt",
            Kapt
        )
    }

}


fun DependencyHandlerScope.installComposeDependencies() {
    Dependencies.Compose.run {
        dependencies.add(
            "implementation",
            UI
        )
        dependencies.add(
            "implementation",
            Activity
        )
        dependencies.add(
            "implementation",
            Material
        )
        dependencies.add(
            "implementation",
            HiltNavigation
        )
        dependencies.add(
            "implementation",
            NavComponent
        )
        dependencies.add(
            "implementation",
            ViewModel
        )
        dependencies.add(
            "debugImplementation",
            Tooling
        )
        dependencies.add(
            "debugImplementation",
            ToolingPreview
        )
        dependencies.add(
            "testImplementation",
            ComposeUITestJunit4
        )
        dependencies.add(
            "debugImplementation",
            ComposeTestManifest
        )
        dependencies.add(
            "debugImplementation",
            CoilCompose
        )
        dependencies.add(
            "debugImplementation",
            LottieCompose
        )
        dependencies.add(
            "debugImplementation",
            ConstraintLayout
        )
    }

}

fun DependencyHandlerScope.installKtorDependencies() {
    Dependencies.Ktor.run {
        dependencies.add(
            "implementation",
            Core
        )
        dependencies.add(
            "implementation",
            CIO
        )
        dependencies.add(
            "implementation",
            Logger
        )
        dependencies.add(
            "implementation",
            ContentNegotiation
        )
        dependencies.add(
            "implementation",
            KTXJson
        )
    }
}



