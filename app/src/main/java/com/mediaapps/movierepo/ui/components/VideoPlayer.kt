package com.mediaapps.movierepo.ui.components

import android.content.Context
import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultDataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
@Composable
fun VideoPlayer(url : String) {
 val context: Context = LocalContext.current
 val exoPlayer = remember(context) {
     ExoPlayer.Builder(context)
         .build().apply {
             val defaultDataSource = DefaultDataSource.Factory(context)
             val dataSourceFactory : DataSource.Factory = DefaultDataSource.Factory(
                 context,
                 defaultDataSource
             )
             val httpDataSource = DefaultHttpDataSource.Factory().apply {
                 setAllowCrossProtocolRedirects(true)
             }
             val mediaSource = ProgressiveMediaSource.Factory(httpDataSource)
                 .createMediaSource(MediaItem.fromUri(Uri.parse(url)))
             playWhenReady = true
             setMediaSource(mediaSource)
             prepare()
         }
 }
    DisposableEffect(
        key1 = AndroidView( factory = {
              PlayerView(it).apply{
                    hideController()
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    player = exoPlayer
                    layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
              }
        })
    ){
     onDispose {
         exoPlayer.release()
     }
    }
}