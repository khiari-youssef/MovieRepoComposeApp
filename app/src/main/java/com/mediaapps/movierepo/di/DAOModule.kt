package com.mediaapps.movierepo.di

import com.mediaapps.movierepo.dataSources.remote.MoviesRESTDataSourceImpl
import com.mediaapps.movierepo.dataSources.remote.MoviesRemoteDAO
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MovieDAORESTImpl

@InstallIn(SingletonComponent::class)
@Module
abstract class DAOModule {

@MovieDAORESTImpl
@Singleton
@Binds
abstract fun bindMoviesRemoteDAOImpl(moviesRESTDataSourceImpl : MoviesRESTDataSourceImpl) : MoviesRemoteDAO

}