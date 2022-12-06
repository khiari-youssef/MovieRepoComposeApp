package com.mediaapps.movierepo.dataSources.remote

import com.mediaapps.movierepo.domain.entities.MovieCatalog
import com.mediaapps.movierepo.domain.entities.MovieProductDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MoviesRESTDataSourceImpl @Inject constructor() : MoviesRemoteDAO {


    override fun fetchMovieProductDetailsAPI(movieID: Int): Flow<MovieProductDetails> {
        TODO("Not yet implemented")
    }

    override fun fetchMoviesCatalogAPI(): Flow<MovieCatalog> {
        TODO("Not yet implemented")
    }
}