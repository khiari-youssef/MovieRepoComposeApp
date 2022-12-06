package com.mediaapps.movierepo.ui.moviesCatalog

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import com.mediaapps.movierepo.domain.entities.MovieCatalog
import com.mediaapps.movierepo.domain.states.MovieCatalogDataState

@Stable
@JvmInline
value class MoviesCatalogStateImmutablePayload(
    val movieCatalog: MovieCatalogDataState
)

data class MoviesCatalogUIStateHolder(
    val moviesCatalog : State<MoviesCatalogStateImmutablePayload>
){
    companion object{

        @Composable
        fun rememberMoviesCatalogUIState(
             moviesCatalog : State<MoviesCatalogStateImmutablePayload>
        ) = remember(moviesCatalog) {
            MoviesCatalogUIStateHolder(
                moviesCatalog
            )
        }
    }
}