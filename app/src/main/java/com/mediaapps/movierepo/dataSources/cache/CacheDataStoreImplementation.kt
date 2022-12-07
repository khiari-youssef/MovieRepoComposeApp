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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppDeviceCacheImplementation @Inject constructor(
    @ApplicationContext  private val appContext: Context,
    private val httpClient : HttpClient
) : AppDeviceCacheDAO {

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

    override fun fetchBaseUrlUpdateFromServer(): Flow<String?> = callbackFlow {
      kotlin.runCatching {
          return@runCatching httpClient.get("/configuration").body<ImageBaseURLConfiguration>()
      }.onFailure {
          channel.close()
      }.onSuccess { result->
          trySend(
              result.serverImage?.run {
                  secureBaseUrl ?: baseUrl
              }
          )
      }

        awaitClose {
            cancel()
        }
    }

}