package com.mediaapps.movierepo.dataSources.cache

import kotlinx.coroutines.flow.Flow

interface AppDeviceCacheDAO {

    suspend fun updateImageBaseUrlCache(imageBaseUrl : String)
    fun collectImageBaseUrl() : Flow<String?>
    fun fetchBaseUrlUpdateFromServer() : Flow<String?>

}