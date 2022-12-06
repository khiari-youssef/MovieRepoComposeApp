package com.mediaapps.movierepo.domain.entities

class MovieCatalog(
    val page : Int,
    val totalPages : Int,
    val movies : List<MovieItem>
)