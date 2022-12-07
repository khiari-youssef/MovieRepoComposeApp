package com.mediaapps.movierepo.dataSources.cache

import kotlinx.coroutines.flow.Flow

interface AppDeviceCacheInterface {

    suspend fun updateImageBaseUrlCache(imageBaseUrl : String)
    fun collectImageBaseUrl() : Flow<String?>
    suspend fun pullBaseUrlChangesFromServer()

}