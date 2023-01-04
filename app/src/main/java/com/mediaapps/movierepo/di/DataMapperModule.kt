package com.mediaapps.movierepo.di

import com.mediaapps.movierepo.dataSources.InterDomainMappingInterface
import com.mediaapps.movierepo.dataSources.remote.MovieCatalogRemoteDTOMapper
import com.mediaapps.movierepo.dataSources.remote.MovieItemRemoteDTOMapper
import com.mediaapps.movierepo.dataSources.remote.MovieProductDetailsRemoteDTOMapper
import com.mediaapps.movierepo.dataSources.remote.MovieVideosDetailsDTOMapper
import com.mediaapps.movierepo.dataSources.remote.networkDTO.MovieCatalogDTO
import com.mediaapps.movierepo.dataSources.remote.networkDTO.MovieItemDTO
import com.mediaapps.movierepo.dataSources.remote.networkDTO.MovieProductDetailsDTO
import com.mediaapps.movierepo.dataSources.remote.networkDTO.MovieVideoDetailsDTO
import com.mediaapps.movierepo.domain.entities.MovieCatalog
import com.mediaapps.movierepo.domain.entities.MovieItem
import com.mediaapps.movierepo.domain.entities.MovieProductDetails
import com.mediaapps.movierepo.domain.entities.MovieVideoDetails
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataMapperModule {

@Singleton
@Binds
abstract fun bindMovieItemRemoteDTOMapper(movieItemRemoteDTOMapper: MovieItemRemoteDTOMapper) : InterDomainMappingInterface<MovieItemDTO, MovieItem>

@Singleton
@Binds
abstract fun bindMovieProductDetailsRemoteDTOMapper(movieProductDetailsRemoteDTOMapper: MovieProductDetailsRemoteDTOMapper) : InterDomainMappingInterface<MovieProductDetailsDTO, MovieProductDetails>

@Singleton
@Binds
abstract fun bindMovieCatalogRemoteDTOMapper(movieCatalogRemoteDTOMapper: MovieCatalogRemoteDTOMapper) : InterDomainMappingInterface<MovieCatalogDTO, MovieCatalog>

@Singleton
@Binds
abstract fun bindMovieVideosDetailsDTOMapper(videosDetailsDTOMapper: MovieVideosDetailsDTOMapper) : InterDomainMappingInterface<MovieVideoDetailsDTO, MovieVideoDetails>
}