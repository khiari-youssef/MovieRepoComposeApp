package com.mediaapps.movierepo.domain.repositories.movies

import com.mediaapps.movierepo.dataSources.cache.AppDeviceCacheInterface
import com.mediaapps.movierepo.dataSources.remote.networkDAO.MoviesRemoteDAO
import com.mediaapps.movierepo.di.MovieDAORESTImpl
import com.mediaapps.movierepo.domain.entities.MovieCatalog
import com.mediaapps.movierepo.domain.entities.MovieProductDetails
import com.mediaapps.movierepo.domain.entities.MovieVideoDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    @MovieDAORESTImpl private val moviesRemoteDAO : MoviesRemoteDAO,
     private val appDeviceCacheInterface : AppDeviceCacheInterface
) : MoviesRepositoryContract {

    override fun fetchMovieProductDetail(movieID: Int): Flow<MovieProductDetails>
    = moviesRemoteDAO.fetchMovieProductDetailsAPI(movieID)
        .flowOn(Dispatchers.IO)


    override fun fetchMoviesCatalog(): Flow<MovieCatalog>
    = moviesRemoteDAO.fetchMoviesCatalogAPI()
        .flowOn(Dispatchers.IO)

    override fun fetchImageServerBaseUrl(): Flow<String?> =
        appDeviceCacheInterface.collectImageBaseUrl()

    override fun fetchMovieVideosByID(movieID: Int): Flow<MovieVideoDetails?>
     = moviesRemoteDAO.fetchMovieVideoByID(movieID)

}