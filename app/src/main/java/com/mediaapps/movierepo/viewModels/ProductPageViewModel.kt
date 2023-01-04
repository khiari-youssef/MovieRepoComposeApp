package com.mediaapps.movierepo.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mediaapps.movierepo.di.MovieRepositoryDevImpl
import com.mediaapps.movierepo.domain.repositories.movies.MoviesRepositoryContract
import com.mediaapps.movierepo.domain.states.MovieProductDetailsDataState
import com.mediaapps.movierepo.domain.utils.reduceException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductPageViewModel @Inject constructor(
    @MovieRepositoryDevImpl private val moviesRepository: MoviesRepositoryContract
) : ViewModel() {

    private val movieProductDetailsMutableState : MutableStateFlow<MovieProductDetailsDataState> = MutableStateFlow(
        MovieProductDetailsDataState.Loading
    )

    val movieProductDetailsImmutableState : StateFlow<MovieProductDetailsDataState> = movieProductDetailsMutableState

    fun getImageBaseUrl() : Flow<String?> = moviesRepository.fetchImageServerBaseUrl()

    fun fetchMovieProductDetail(movieID : Int){
        viewModelScope.launch {
            moviesRepository.fetchMovieProductDetail(movieID)
                .reduceException{ domainException->
                    domainException.printStackTrace()
                    movieProductDetailsMutableState.value = MovieProductDetailsDataState.Error(domainException)
                }
                .collectLatest { movieProductDetails->
                    movieProductDetailsMutableState.value = MovieProductDetailsDataState.Success(movieProductDetails)
                }
        }
    }

    fun getMovieVideoOverView(movieID: Int) : Flow<String?> =
        moviesRepository
            .fetchMovieVideosByID(movieID)
            .map {
             it?.url
             }.flowOn(viewModelScope.coroutineContext)

}