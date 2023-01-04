package com.mediaapps.movierepo.dataSources.remote.networkDTO

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
@JvmInline
value class MovieVideoDetailsDTOWrapper(
@SerialName("results") val results : List<MovieVideoDetailsDTO>
)


@Serializable
data class MovieVideoDetailsDTO(
 @SerialName("id")   val id : String,
 @SerialName("name")   val name : String,
 @SerialName("key")   val key : String,
 @SerialName("iso_639_1")  val iso_639_1_lang : String,
 @SerialName("iso_3166_1") val so_3166_1_lang : String,
 @SerialName("site") val site : String,
 @SerialName("size")   val size : Long,
 @SerialName("type")   val type : String,
 @SerialName("published_at")   val publishDateTime : String,
 @SerialName("official")  val isOfficial : Boolean
)
