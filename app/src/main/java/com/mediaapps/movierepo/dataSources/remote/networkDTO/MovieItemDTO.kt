package com.mediaapps.movierepo.dataSources.remote.networkDTO

import java.time.LocalDate

data class MovieItemDTO(
    val id : Int,
    val title : String,
    val poster : String,
    val releaseDate : LocalDate
)