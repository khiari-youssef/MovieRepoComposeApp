package com.mediaapps.movierepo.base

import android.app.Application
import com.mediaapps.movierepo.dataSources.cache.AppDeviceCacheDAO
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MovieRepoApplication : Application() {


    override fun onCreate() {
        super.onCreate()
    }
}