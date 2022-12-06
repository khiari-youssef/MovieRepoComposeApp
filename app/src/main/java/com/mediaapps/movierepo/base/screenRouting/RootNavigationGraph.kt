package com.mediaapps.movierepo.base

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mediaapps.movierepo.base.screenRouting.MovieProductPageScreenRoute
import com.mediaapps.movierepo.base.screenRouting.MoviesCatalogScreenRoute
import com.mediaapps.movierepo.ui.movieProductPage.MovieProductPageScreen
import com.mediaapps.movierepo.ui.moviesCatalog.MoviesCatalogScreen

@Composable
fun  RootNavigationGraph() {
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = MoviesCatalogScreenRoute
    ){
        composable(
            route = MoviesCatalogScreenRoute
        ){
            MoviesCatalogScreen()
        }
        composable(
            route  =MovieProductPageScreenRoute
        ){
            MovieProductPageScreen()
        }
    }
}