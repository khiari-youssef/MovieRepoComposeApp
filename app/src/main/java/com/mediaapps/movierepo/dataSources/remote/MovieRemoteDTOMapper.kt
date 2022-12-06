package com.mediaapps.movierepo.dataSources.remote

import com.mediaapps.movierepo.dataSources.InterDomainMappingInterface
import com.mediaapps.movierepo.dataSources.remote.networkDTO.MovieCatalogDTO
import com.mediaapps.movierepo.dataSources.remote.networkDTO.MovieItemDTO
import com.mediaapps.movierepo.dataSources.remote.networkDTO.MovieProductDetailsDTO
import com.mediaapps.movierepo.domain.entities.MovieCatalog
import com.mediaapps.movierepo.domain.entities.MovieItem
import com.mediaapps.movierepo.domain.entities.MovieProductDetails
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class MovieItemRemoteDTOMapper @Inject constructor() : InterDomainMappingInterface<MovieItemDTO,MovieItem> {

    override fun fromStartToDestination(start: MovieItemDTO): MovieItem = MovieItem(
        id = start.id ?: -1,
        title = start.title ?: "",
        poster = start.poster ?: "",
        releaseDate = LocalDate.from(DateTimeFormatter.ISO_DATE.parse(start.releaseDate))
    )

    override fun fromDestinationToStart(destination: MovieItem): MovieItemDTO {
        TODO("Not yet implemented, we don't need the inverse mapping yet")
    }
}

class MovieProductDetailsRemoteDTOMapper @Inject constructor() : InterDomainMappingInterface<MovieProductDetailsDTO, MovieProductDetails> {

    override fun fromStartToDestination(start: MovieProductDetailsDTO): MovieProductDetails = MovieProductDetails(
        id =start.id ?: -1,
        title = start.title ?: "",
        poster = start.poster ?: "",
        releaseDate = LocalDate.from(DateTimeFormatter.ISO_DATE.parse(start.releaseDate)),
        overView = start.overView ?: ""
    )

    override fun fromDestinationToStart(destination: MovieProductDetails): MovieProductDetailsDTO {
        TODO("Not yet implemented, we don't need the inverse mapping yet")
    }


}

class MovieCatalogRemoteDTOMapper @Inject constructor(
    private val movieMapper : InterDomainMappingInterface<MovieItemDTO,MovieItem>
) : InterDomainMappingInterface<MovieCatalogDTO, MovieCatalog> {

    override fun fromStartToDestination(start: MovieCatalogDTO): MovieCatalog = MovieCatalog(
        page = start.page ?: -1,
        totalPages = start.totalPages ?: -1,
        movies = start.movies?.map {
            movieMapper.fromStartToDestination(it)
        } ?: listOf()
    )

    override fun fromDestinationToStart(destination: MovieCatalog): MovieCatalogDTO {
        TODO("Not yet implemented, we don't need the inverse mapping yet")
    }

}