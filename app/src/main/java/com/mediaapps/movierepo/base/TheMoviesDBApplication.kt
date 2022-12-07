package com.mediaapps.movierepo.base

import android.app.Application
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mediaapps.movierepo.dataSources.cache.AppDeviceCacheInterface
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class TheMoviesDBApplication : Application() {

    @Inject
    lateinit var appDeviceCacheInterface : AppDeviceCacheInterface

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().run {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.CREATED) {
                         appDeviceCacheInterface.pullBaseUrlChangesFromServer()
                }
            }
        }
    }
}