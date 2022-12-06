package com.mediaapps.movierepo.domain.repositories

import com.mediaapps.movierepo.dataSources.remote.MoviesRemoteDAO
import com.mediaapps.movierepo.di.MovieDAORESTImpl
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    @MovieDAORESTImpl private val moviesRemoteDAO : MoviesRemoteDAO
) : MoviesRepositoryContract {

}