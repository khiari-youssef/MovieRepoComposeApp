package com.mediaapps.movierepo.dataSources.remote.networkDAO

import com.mediaapps.movierepo.domain.entities.MovieCatalog
import com.mediaapps.movierepo.domain.entities.MovieProductDetails
import com.mediaapps.movierepo.domain.entities.MovieVideoDetails
import kotlinx.coroutines.flow.Flow

interface MoviesRemoteDAO {

fun fetchMoviesCatalogAPI() : Flow<MovieCatalog>

fun fetchMovieProductDetailsAPI(movieID : Int) : Flow<MovieProductDetails>

fun fetchMovieVideoByID(movieID: Int) : Flow<MovieVideoDetails?>


}