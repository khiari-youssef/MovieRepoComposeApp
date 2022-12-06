package com.mediaapps.movierepo.domain.entities

import java.time.LocalDate

class MovieProductDetails(
     id : Int,
     title : String,
     poster : String,
     releaseDate : LocalDate,
    val  overView : String,
    language : String?=null,
      isForAdult : Boolean = false
) : MovieItem(
    id,
    title,
    poster,
    releaseDate,
    language,
    isForAdult
){
    override fun toString(): String {
        return "MovieProductDetails(overView='$overView',${super.toString()})"
    }
}