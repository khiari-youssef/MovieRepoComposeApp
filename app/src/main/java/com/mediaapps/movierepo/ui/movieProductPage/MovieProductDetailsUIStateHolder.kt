package com.mediaapps.movierepo.ui.movieProductPage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import com.mediaapps.movierepo.domain.states.MovieCatalogDataState
import com.mediaapps.movierepo.domain.states.MovieProductDetailsDataState
import com.mediaapps.movierepo.ui.collectAsStateLifecycleAware
import com.mediaapps.movierepo.ui.moviesCatalog.MoviesCatalogStateImmutablePayload
import com.mediaapps.movierepo.viewModels.MoviesCatalogViewModel
import com.mediaapps.movierepo.viewModels.ProductPageViewModel


data class MovieProductDetailsUIStateHolder(
    val movieProductPage : State<MovieProductDetailsDataState>,
    val baseUrlState : State<String?>
){
    companion object{

        @Composable
        fun rememberMovieProductDetailsUIState(
            viewModel : ProductPageViewModel
        ) =
            MovieProductDetailsUIStateHolder(
                movieProductPage = viewModel
                    .movieProductDetailsImmutableState
                    .collectAsStateLifecycleAware(initial = MovieProductDetailsDataState.Loading ),
                baseUrlState = viewModel.getImageBaseUrl().collectAsStateLifecycleAware(initial = null)
            )


    }
}