package com.mediaapps.movierepo.base

import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
            MoviesCatalogScreen(
                onMovieClicked = remember {
                    {
                        rootNavController.navigate(
                           route =  "$MovieProductPageScreenRoute/${it.id}"
                        )
                    }
                }
            )
        }
        composable(
            route  = "${MovieProductPageScreenRoute}/{movieID}",
            arguments = listOf(
                navArgument("movieID"){
                    type = NavType.IntType
                }
            )
        ){ args->
           val movieID : Int = args.arguments?.
           getInt("movieID",-1) ?: -1
            MovieProductPageScreen(
                movieID = movieID
            )
        }
    }
}