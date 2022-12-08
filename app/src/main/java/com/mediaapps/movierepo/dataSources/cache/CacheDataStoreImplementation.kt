package com.mediaapps.movierepo.dataSources.cache

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.mediaapps.movierepo.dataSources.remote.networkDTO.ImageBaseURLConfiguration
import dagger.hilt.android.qualifiers.ApplicationContext
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppDeviceCacheImplementation @Inject constructor(
    @ApplicationContext  private val appContext: Context,
    private val httpClient : HttpClient
) : AppDeviceCacheInterface {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private val imageBaseUrlKey : Preferences.Key<String> = stringPreferencesKey("image_base_url")


    override suspend fun updateImageBaseUrlCache(imageBaseUrl : String){
        withContext(Dispatchers.IO){
            appContext.dataStore.edit { mutablePrefs->
                mutablePrefs.set(imageBaseUrlKey,imageBaseUrl)
            }
        }
    }

     override fun collectImageBaseUrl() : Flow<String?> = appContext.dataStore.data
         .map { immutablePrefs->
         immutablePrefs[imageBaseUrlKey]
     }

    override suspend fun pullBaseUrlChangesFromServer() {
     withContext(Dispatchers.IO) {
         kotlin.runCatching {
             return@runCatching httpClient.get {
                 url {
                     appendPathSegments(
                         "configuration"
                     )
                 }
             }.body<ImageBaseURLConfiguration>()
         }.onFailure {
             it.printStackTrace()
         }.onSuccess { result->
             result.serverImage?.run {
                 secureBaseUrl ?: baseUrl
             }?.run {
                 updateImageBaseUrlCache(
                     this
                 )
             }
         }
     }

    }

}