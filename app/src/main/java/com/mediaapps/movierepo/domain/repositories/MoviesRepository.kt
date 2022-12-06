package com.mediaapps.movierepo.domain.repositories

import com.mediaapps.movierepo.dataSources.remote.MoviesRemoteDAO
import com.mediaapps.movierepo.di.MovieDAORESTImpl
import com.mediaapps.movierepo.domain.entities.MovieCatalog
import com.mediaapps.movierepo.domain.entities.MovieProductDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    @MovieDAORESTImpl private val moviesRemoteDAO : MoviesRemoteDAO
) : MoviesRepositoryContract {

    override fun fetchMovieProductDetail(movieID: Int): Flow<MovieProductDetails>
    = moviesRemoteDAO.fetchMovieProductDetailsAPI(movieID)

    override fun fetchMoviesCatalog(): Flow<MovieCatalog>
    = moviesRemoteDAO.fetchMoviesCatalogAPI()

}