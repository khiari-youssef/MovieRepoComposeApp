package com.mediaapps.movierepo.domain.repositories

import com.mediaapps.movierepo.domain.entities.MovieCatalog
import com.mediaapps.movierepo.domain.entities.MovieProductDetails
import kotlinx.coroutines.flow.Flow

interface MoviesRepositoryContract {

fun fetchMoviesCatalog() : Flow<MovieCatalog>

fun fetchMovieProductDetail(movieID : Int) : Flow<MovieProductDetails>

}