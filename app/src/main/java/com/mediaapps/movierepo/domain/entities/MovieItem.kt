package com.mediaapps.movierepo.domain.entities

import java.time.LocalDate


open class MovieItem(
    val id : Int,
    val title : String,
    val poster : String,
    val releaseDate : LocalDate
)