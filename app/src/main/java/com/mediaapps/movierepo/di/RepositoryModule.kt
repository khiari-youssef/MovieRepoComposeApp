package com.mediaapps.movierepo.di

import com.mediaapps.movierepo.domain.repositories.movies.MoviesRepository
import com.mediaapps.movierepo.domain.repositories.movies.MoviesRepositoryContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Qualifier

@Retention(AnnotationRetention.BINARY)
@Qualifier
annotation class MovieRepositoryDevImpl


@InstallIn(ViewModelComponent::class)
@Module
abstract class RepositoryModule {

@MovieRepositoryDevImpl
@Binds
abstract fun bindMoviesRepositoryInstance(moviesRepository: MoviesRepository) : MoviesRepositoryContract

}