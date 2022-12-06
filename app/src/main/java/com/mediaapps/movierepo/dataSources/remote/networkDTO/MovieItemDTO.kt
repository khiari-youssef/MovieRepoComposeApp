package com.mediaapps.movierepo.dataSources.remote.networkDTO

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class MovieItemDTO(
@SerialName("id")    val id : Int,
@SerialName("title")    val title : String,
@SerialName("poster_path")     val poster : String,
@SerialName("release_date")     val releaseDate : String
)