package com.mediaapps.movierepo.domain.repositories.movies

import com.mediaapps.movierepo.domain.entities.MovieCatalog
import com.mediaapps.movierepo.domain.entities.MovieProductDetails
import com.mediaapps.movierepo.domain.entities.MovieVideoDetails
import kotlinx.coroutines.flow.Flow

interface MoviesRepositoryContract {

fun fetchMoviesCatalog() : Flow<MovieCatalog>

fun fetchMovieProductDetail(movieID : Int) : Flow<MovieProductDetails>

fun fetchImageServerBaseUrl() : Flow<String?>

fun fetchMovieVideosByID(movieID: Int) : Flow<MovieVideoDetails?>

}