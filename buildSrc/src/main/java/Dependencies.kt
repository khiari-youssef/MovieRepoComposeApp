


object Dependencies {


    object Kotlin{
        const val Core : String = "androidx.core:core-ktx:${Versions.Kotlin.Core}"
        const val Coroutines : String = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.Coroutines}"
        const val LifeCycleRuntime : String = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Kotlin.LifeCycleRuntime}"
        const val Junit4 : String = "junit:junit:${Versions.Kotlin.Junit4}"
        const val TextExt : String = "androidx.test.ext:junit:${Versions.Kotlin.TextExt}"
        const val EspressoTest : String = "androidx.test.espresso:espresso-core:${Versions.Kotlin.EspressoTest}"
        const val ViewModel : String = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Kotlin.LifeCycleRuntime}"
    }

    object Compose {
        const val Activity : String = "androidx.activity:activity-compose:${Versions.Compose.Activity}"
        const val UI : String = "androidx.compose.ui:ui:${Versions.Compose.UI}"
        const val Tooling : String = "androidx.compose.ui:ui-tooling:${Versions.Compose.Tooling}"
        const val ToolingPreview : String = "androidx.compose.ui:ui-tooling-preview:${Versions.Compose.ToolingPreview}"
        const val Material : String = "androidx.compose.material:material:${Versions.Compose.Material}"
        const val ViewModel : String = "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.Compose.ViewModel}"
        const val HiltNavigation : String = "androidx.hilt:hilt-navigation-compose:1.0.0"
        const val CoilCompose : String = "io.coil-kt:coil-compose:${Versions.Compose.CoilCompose}"
        const val LottieCompose : String = "com.airbnb.android:lottie-compose:${Versions.Compose.Lottie}"
        const val NavComponent : String = "androidx.navigation:navigation-compose:${Versions.Compose.NavComponent}"
        const val ComposeUITestJunit4 : String = "androidx.compose.ui:ui-test-junit4:${Versions.Compose.ComposeUITestJunit4}"
        const val ComposeTestManifest : String ="androidx.compose.ui:ui-test-manifest:${Versions.Compose.ComposeTestManifest}"
    }

    object Hilt{
        const val Hilt = "com.google.dagger:hilt-android:2.44"
        const val Kapt = "com.google.dagger:hilt-android-compiler:2.44"
    }

    object Ktor{
      const val Core : String = "io.ktor:ktor-client-core:${Versions.Ktor.Core}"
      const val CIO : String = "io.ktor:ktor-client-cio:${Versions.Ktor.CIO}"
      const val Logger : String = "io.ktor:ktor-client-logging:${Versions.Ktor.Logger}"
      const val ContentNegotiation : String = "io.ktor:ktor-client-content-negotiation:${Versions.Ktor.ContentNegotiation}"
      const val KTXJson : String =  "io.ktor:ktor-serialization-kotlinx-json:${Versions.Ktor.KTXJson}"
    }


}