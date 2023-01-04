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
 @SerialName("name")   val name : String?=null,
 @SerialName("key")   val key : String?=null,
 @SerialName("iso_639_1")  val iso_639_1_lang : String?=null,
 @SerialName("iso_3166_1") val so_3166_1_lang : String?=null,
 @SerialName("site") val site : String?=null,
 @SerialName("size")   val size : Long?=null,
 @SerialName("type")   val type : String?=null,
 @SerialName("published_at")   val publishDateTime : String?,
 @SerialName("official")  val isOfficial : Boolean?=null
)
