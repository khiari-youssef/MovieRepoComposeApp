package com.mediaapps.movierepo.ui.moviesCatalog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.mediaapps.movierepo.domain.states.MovieCatalogDataState
import com.mediaapps.movierepo.viewModels.MoviesCatalogViewModel
import kotlinx.coroutines.flow.map

@Composable
fun  MoviesCatalogScreen(
    viewModel: MoviesCatalogViewModel = hiltViewModel(),
    uiStateHolder : MoviesCatalogUIStateHolder = MoviesCatalogUIStateHolder.rememberMoviesCatalogUIState(
        moviesCatalog = viewModel.moviesCatalogImmutableState
            .map {
                MoviesCatalogStateImmutablePayload(
                    it
                )
            }
            .collectAsState(initial = MoviesCatalogStateImmutablePayload(MovieCatalogDataState.Loading) )
    )
) {

}