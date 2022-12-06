package com.mediaapps.movierepo.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mediaapps.movierepo.di.MovieRepositoryDevImpl
import com.mediaapps.movierepo.domain.repositories.MoviesRepository
import com.mediaapps.movierepo.domain.repositories.MoviesRepositoryContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesCatalogViewModel @Inject constructor(
    @MovieRepositoryDevImpl private val moviesRepository: MoviesRepositoryContract
) : ViewModel() {

}