package com.mediaapps.movierepo.domain.repositories

import com.mediaapps.movierepo.dataSources.remote.MoviesRemoteDAO
import com.mediaapps.movierepo.di.MovieDAORESTImpl
import com.mediaapps.movierepo.domain.entities.MovieCatalog
import com.mediaapps.movierepo.domain.entities.MovieProductDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    @MovieDAORESTImpl private val moviesRemoteDAO : MoviesRemoteDAO
) : MoviesRepositoryContract {

    override fun fetchMovieProductDetail(movieID: Int): Flow<MovieProductDetails>
    = moviesRemoteDAO.fetchMovieProductDetailsAPI(movieID)
        .flowOn(Dispatchers.IO)

    override fun fetchMoviesCatalog(): Flow<MovieCatalog>
    = moviesRemoteDAO.fetchMoviesCatalogAPI()
        .flowOn(Dispatchers.IO)

}