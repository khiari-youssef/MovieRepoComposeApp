package com.mediaapps.movierepo.dataSources.remote.networkDTO


data class MovieCatalogDTO(
    val page : Int,
    val totalPages : Int,
    val movies : List<MovieItemDTO>
)