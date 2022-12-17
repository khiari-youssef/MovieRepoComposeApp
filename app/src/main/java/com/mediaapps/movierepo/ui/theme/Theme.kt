package com.mediaapps.movierepo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = LightCornflowerBlue,
    primaryVariant = PattensBlue,
    secondary = GoldDrop,
    secondaryVariant = OrangePeel,
    surface = BlackPearl,
    onSurface = Color.White,
    background = Color.Black,
    onBackground = Color.White,
    error = Color.Red,
    onError = Color.White
)

private val LightColorPalette = lightColors(
    primary = DarkCerulean,
    primaryVariant = NiceBlue,
    secondary = GoldDrop,
    secondaryVariant = OrangePeel,
    surface = Porcelain,
    onSurface = Color.Black,
    background = Color.White,
    onBackground = Color.Black,
    error = Color.Red,
    onError = Color.White
)

@Composable
fun MovieRepoTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}