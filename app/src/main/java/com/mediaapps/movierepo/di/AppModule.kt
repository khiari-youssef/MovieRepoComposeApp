package com.mediaapps.movierepo.di

import com.mediaapps.movierepo.BuildConfig
import com.mediaapps.movierepo.domain.exceptions.MovieDomainException
import com.mediaapps.movierepo.domain.exceptions.MovieInvalidAPIKeyException
import com.mediaapps.movierepo.domain.exceptions.MovieResourceNotFoundException
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton



@InstallIn(SingletonComponent::class)
@Module
class AppModule {

companion object {
 private const val API_VERSION : Int = 3
}

@Singleton
@Provides
fun provideAppHttpClient() : HttpClient = HttpClient(CIO){
  install(Logging)
  developmentMode = BuildConfig.DEBUG
  install(ContentNegotiation) {
    json(Json {
      prettyPrint = true
      isLenient = true
      ignoreUnknownKeys = true
    })
  }
  install(DefaultRequest){
    url {
      url("https://api.themoviedb.org")
      path("$API_VERSION/")
      parameters.append(
          "api_key",BuildConfig.API_KEY
      )
      headers.append(
        HttpHeaders.ContentType, "application/json"
      )
    }
  }
  HttpResponseValidator {
    validateResponse {
      if (it.status.isSuccess().not()) {
        when (it.status.value){
          404 -> throw MovieResourceNotFoundException()
          401 -> throw MovieInvalidAPIKeyException()
          else -> throw MovieDomainException()
        }
      }
    }
  }
  engine {
    requestTimeout = 5000
  }
}


}