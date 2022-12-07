package com.mediaapps.movierepo.ui.moviesCatalog

import androidx.compose.runtime.*
import com.mediaapps.movierepo.domain.entities.MovieCatalog
import com.mediaapps.movierepo.domain.states.MovieCatalogDataState
import com.mediaapps.movierepo.ui.collectAsStateLifecycleAware
import com.mediaapps.movierepo.viewModels.MoviesCatalogViewModel
import kotlinx.coroutines.flow.map

@Stable
@JvmInline
value class MoviesCatalogStateImmutablePayload(
    val movieCatalog: MovieCatalogDataState
)

data class MoviesCatalogUIStateHolder(
    val moviesCatalog : State<MoviesCatalogStateImmutablePayload>,
    val baseUrlState : State<String?>
){
    companion object{

        @Composable
        fun rememberMoviesCatalogUIState(
             viewModel : MoviesCatalogViewModel
        ) = viewModel
            .moviesCatalogImmutableState
            .collectAsStateLifecycleAware(initial = MovieCatalogDataState.Loading )
            .value.let {
                MoviesCatalogUIStateHolder(
                    moviesCatalog = derivedStateOf {
                        MoviesCatalogStateImmutablePayload(it)
                    },
                    baseUrlState = viewModel.getImageBaseUrl().collectAsStateLifecycleAware(initial = null)
                )
            }


    }
}