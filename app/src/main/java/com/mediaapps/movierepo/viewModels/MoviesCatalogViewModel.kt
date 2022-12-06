package com.mediaapps.movierepo.viewModels

import androidx.lifecycle.ViewModel
import com.mediaapps.movierepo.domain.repositories.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesCatalogViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {
}