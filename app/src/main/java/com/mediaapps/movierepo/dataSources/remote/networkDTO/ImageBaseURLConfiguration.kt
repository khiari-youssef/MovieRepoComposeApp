package com.mediaapps.movierepo.dataSources.remote.networkDTO

import kotlinx.serialization.SerialName


@kotlinx.serialization.Serializable
data class ServerImage(
@SerialName("base_url")    val baseUrl : String?,
@SerialName("secure_base_url")    val secureBaseUrl : String?,
@SerialName("poster_sizes")   val posterSizes : List<String>?
)

@kotlinx.serialization.Serializable
data class ImageBaseURLConfiguration(
@SerialName("images")  val serverImage: ServerImage?
)