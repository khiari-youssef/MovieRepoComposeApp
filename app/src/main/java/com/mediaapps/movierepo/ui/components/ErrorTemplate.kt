package com.mediaapps.movierepo.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mediaapps.movierepo.R

@Composable
fun ErrorScreenBox(
    messageRes : Int = R.string.error_message_any_error,
    lottieRes : Int = R.raw.error
) {
    Box(modifier = Modifier
        .padding(
            top = 64.dp
        )
        .fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            val lottieAnime  by rememberLottieComposition(
                LottieCompositionSpec.RawRes(lottieRes)
            )
            LottieAnimation(
                composition = lottieAnime,
                modifier = Modifier.size(300.dp)
            )
            Text(
                text =  stringResource(id = messageRes ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 24.sp,
                fontWeight = FontWeight(700),
                fontFamily = FontFamily(Font(R.font.satoshi_bold)),
                lineHeight = 41.sp,
                color = Color(0xFFC7ACAC)
            )
        }
    }
}