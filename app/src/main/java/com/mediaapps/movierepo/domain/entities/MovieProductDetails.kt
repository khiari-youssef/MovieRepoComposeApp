package com.mediaapps.movierepo.domain.entities

import java.time.LocalDate

class MovieProductDetails(
     id : Int,
     title : String,
     poster : String,
     releaseDate : LocalDate,
    val  overView : String
) : MovieItem(
    id,
    title,
    poster,
    releaseDate
){
    override fun toString(): String {
        return "MovieProductDetails(overView='$overView',${super.toString()})"
    }
}