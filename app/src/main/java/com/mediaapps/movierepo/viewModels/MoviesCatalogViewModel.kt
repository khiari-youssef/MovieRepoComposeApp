package com.mediaapps.movierepo.viewModels

import androidx.lifecycle.ViewModel
import com.mediaapps.movierepo.di.MovieRepositoryDevImpl
import com.mediaapps.movierepo.domain.repositories.MoviesRepository
import com.mediaapps.movierepo.domain.repositories.MoviesRepositoryContract
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesCatalogViewModel @Inject constructor(
    @MovieRepositoryDevImpl private val moviesRepository: MoviesRepositoryContract
) : ViewModel() {
}