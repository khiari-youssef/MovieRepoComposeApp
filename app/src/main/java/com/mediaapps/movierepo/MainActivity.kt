package com.mediaapps.movierepo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mediaapps.movierepo.base.RootNavigationGraph
import com.mediaapps.movierepo.ui.theme.MovieRepoTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieRepoTheme {
                RootNavigationGraph()
            }
        }
    }
}
