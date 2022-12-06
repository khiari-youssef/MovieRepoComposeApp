package com.mediaapps.movierepo.dataSources.remote.networkDTO

import kotlinx.serialization.SerialName
import java.time.LocalDate


@kotlinx.serialization.Serializable
data class MovieProductDetailsDTO(
    @SerialName("id")  val id : Int,
    @SerialName("title")  val title : String,
    @SerialName("poster_path")     val poster : String,
    @SerialName("release_date")     val releaseDate : String,
    @SerialName("overview") val  overView : String
)