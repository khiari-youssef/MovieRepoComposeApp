package com.mediaapps.movierepo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import javax.inject.Singleton



@InstallIn(SingletonComponent::class)
@Module
class AppModule {

@Singleton
@Provides
fun provideAppHttpClient() : HttpClient = HttpClient(CIO){

}


}