package com.mediaapps.movierepo.dataSources.remote

import com.mediaapps.movierepo.dataSources.InterDomainMappingInterface
import com.mediaapps.movierepo.dataSources.remote.networkDTO.MovieVideoDetailsDTO
import com.mediaapps.movierepo.domain.entities.MovieVideoDetails
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class MovieVideosDetailsDTOMapper @Inject constructor() : InterDomainMappingInterface<MovieVideoDetailsDTO,MovieVideoDetails> {

    override fun fromDestinationToStart(destination: MovieVideoDetails): MovieVideoDetailsDTO {
        TODO("Not yet implemented")
    }

    override fun fromStartToDestination(start: MovieVideoDetailsDTO): MovieVideoDetails = MovieVideoDetails(
        id = start.id,
        name = start.name ?: "",
        url = "https://www.youtube.com/watch?v=${start.key}",
        lang = start.iso_639_1_lang ?: start.so_3166_1_lang ?: "",
        size = start.size ?: -1,
        type = start.type ?: "",
        publishDateTime = start.publishDateTime?.runCatching {
               LocalDateTime.from(
                   DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(
                       this
                   )
               )
        }?.getOrNull(),
        isOfficial = start.isOfficial
    )
}