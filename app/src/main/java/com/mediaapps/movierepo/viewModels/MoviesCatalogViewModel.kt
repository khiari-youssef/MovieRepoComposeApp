package com.mediaapps.movierepo.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mediaapps.movierepo.di.MovieRepositoryDevImpl
import com.mediaapps.movierepo.domain.exceptions.MovieDomainException
import com.mediaapps.movierepo.domain.exceptions.MovieResourceNotFoundException
import com.mediaapps.movierepo.domain.repositories.MoviesRepository
import com.mediaapps.movierepo.domain.repositories.MoviesRepositoryContract
import com.mediaapps.movierepo.domain.states.MovieCatalogDataState
import com.mediaapps.movierepo.domain.utils.reduceException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesCatalogViewModel @Inject constructor(
    @MovieRepositoryDevImpl private val moviesRepository: MoviesRepositoryContract
) : ViewModel() {

    private val moviesCatalogMutableState : MutableStateFlow<MovieCatalogDataState> = MutableStateFlow(
        MovieCatalogDataState.Loading
    )

     val moviesCatalogImmutableState : StateFlow<MovieCatalogDataState> = moviesCatalogMutableState

    fun refreshMovieCatalog() {
        viewModelScope.launch {
            moviesRepository.fetchMoviesCatalog()
                .reduceException{ domainException->
                    domainException.printStackTrace()
                    moviesCatalogMutableState.value = MovieCatalogDataState.Error(domainException)
                }
                .collectLatest { catalog->
                moviesCatalogMutableState.value = if (catalog.movies.isEmpty()){
                    MovieCatalogDataState.Success.Empty
                } else {
                    MovieCatalogDataState.Success.Empty
                }
            }
        }
    }




}