package com.mediaapps.movierepo.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ErrorScreenBox(
    messageRes : Int = 0,
    imageRes : Int = 0
) {
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Error")
    }
}