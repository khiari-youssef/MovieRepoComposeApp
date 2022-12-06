package com.mediaapps.movierepo.dataSources.remote

import com.mediaapps.movierepo.BuildConfig
import com.mediaapps.movierepo.dataSources.InterDomainMappingInterface
import com.mediaapps.movierepo.dataSources.remote.networkDTO.MovieCatalogDTO
import com.mediaapps.movierepo.dataSources.remote.networkDTO.MovieProductDetailsDTO
import com.mediaapps.movierepo.domain.entities.MovieCatalog
import com.mediaapps.movierepo.domain.entities.MovieProductDetails
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject


class MoviesRESTDataSourceImpl @Inject constructor(
    private val httpClient: HttpClient,
    private val movieCatalogRemoteDTOMapper: InterDomainMappingInterface<MovieCatalogDTO, MovieCatalog>,
    private val moviesProductDetailsRemoteDTOMapper: InterDomainMappingInterface<MovieProductDetailsDTO, MovieProductDetails>
) : MoviesRemoteDAO {


    override fun fetchMovieProductDetailsAPI(movieID: Int): Flow<MovieProductDetails> = callbackFlow  {
        kotlin.runCatching {
            return@runCatching httpClient.get{
                url {
                    appendPathSegments(
                        "movie",
                        "$movieID"
                    )
                }
            }.body<MovieProductDetailsDTO>().let {
                moviesProductDetailsRemoteDTOMapper.fromStartToDestination(it)
            }
        }.onFailure {  ex->
            channel.close(ex)
        }.onSuccess { movieProductDetails->
            trySend(
                movieProductDetails
            )
        }
    }

    override fun fetchMoviesCatalogAPI(): Flow<MovieCatalog> = callbackFlow {
        kotlin.runCatching {
            return@runCatching httpClient.get("discover/movie")
                .body<MovieCatalogDTO>().let {
                movieCatalogRemoteDTOMapper.fromStartToDestination(it)
            }
        }.onFailure {  ex->
          channel.close(ex)
        }.onSuccess { movieCatalog->
            trySend(
                movieCatalog
            )
        }
        awaitClose {
            cancel()
        }
    }
}