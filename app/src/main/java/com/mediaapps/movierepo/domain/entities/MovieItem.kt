package com.mediaapps.movierepo.domain.entities

import java.time.LocalDate


open class MovieItem(
    val id : Int,
    val title : String,
    val poster : String,
    val releaseDate : LocalDate
){
    override fun toString(): String {
        return "MovieItem(id=$id, title='$title', poster='$poster', releaseDate=$releaseDate)"
    }
}