package com.mediaapps.movierepo.dataSources.remote.networkDTO

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class MovieCatalogDTO(
 @SerialName("page")   val page : Int,
 @SerialName("total_pages")   val totalPages : Int,
 @SerialName("results")   val movies : List<MovieItemDTO>
)