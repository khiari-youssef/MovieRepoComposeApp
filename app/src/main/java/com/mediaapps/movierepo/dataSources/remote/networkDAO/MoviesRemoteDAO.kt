package com.mediaapps.movierepo.dataSources.remote.networkDAO

import com.mediaapps.movierepo.domain.entities.MovieCatalog
import com.mediaapps.movierepo.domain.entities.MovieProductDetails
import kotlinx.coroutines.flow.Flow

interface MoviesRemoteDAO {

fun fetchMoviesCatalogAPI() : Flow<MovieCatalog>

fun fetchMovieProductDetailsAPI(movieID : Int) : Flow<MovieProductDetails>

}