package com.mediaapps.movierepo.dataSources.remote.networkDTO

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class MovieItemDTO(
@SerialName("id")    val id : Int?=null,
@SerialName("title")    val title : String?=null,
@SerialName("poster_path")     val poster : String?=null,
@SerialName("release_date")     val releaseDate : String?=null,
@SerialName("original_language") val language : String?=null,
@SerialName("adult") val isForAdult : Boolean = false
)