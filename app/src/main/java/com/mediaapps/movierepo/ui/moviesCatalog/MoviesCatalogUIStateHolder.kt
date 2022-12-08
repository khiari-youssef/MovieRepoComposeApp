package com.mediaapps.movierepo.ui.moviesCatalog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import com.mediaapps.movierepo.domain.states.MovieCatalogDataState
import com.mediaapps.movierepo.ui.collectAsStateLifecycleAware
import com.mediaapps.movierepo.viewModels.MoviesCatalogViewModel

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