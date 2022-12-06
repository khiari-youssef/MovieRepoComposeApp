package com.mediaapps.movierepo.domain.entities

class MovieCatalog(
    val page : Int,
    val totalPages : Int,
    val movies : List<MovieItem>
){
    override fun toString(): String {
        return "MovieCatalog(page=$page, totalPages=$totalPages, movies=$movies)"
    }
}