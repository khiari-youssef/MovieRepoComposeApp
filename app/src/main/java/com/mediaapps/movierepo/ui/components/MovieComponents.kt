package com.mediaapps.movierepo.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mediaapps.movierepo.R


@Preview
@Composable
fun AdultMark(
    modifier: Modifier = Modifier,
    isVisible : Boolean = true
) {
    AnimatedVisibility(
        visible = isVisible,
        modifier = modifier
    ) {
        Box(
            modifier = modifier
                .border(
                    border = BorderStroke(1.dp, Color.Red),
                    shape = CircleShape
                )
                .background(
                    shape = CircleShape,
                    color = Color.White
                )
                .wrapContentSize()
                .padding(6.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "+18",
                fontSize = 10.sp,
                color = Color.Red,
                modifier = Modifier.wrapContentSize(),
                fontFamily = FontFamily(Font(R.font.satoshi_bold))
            )
        }
    }
}


@Preview
@Composable
fun MovieLangMark(
    modifier: Modifier = Modifier,
    lang : String? = "en"
) {
    AnimatedVisibility(
        visible = lang != null,
        modifier = modifier
    ) {
        Box(
            modifier = modifier
                .border(
                    border = BorderStroke(1.dp, MaterialTheme.colors.primary),
                    shape = RoundedCornerShape(8.dp)
                )
                .background(
                    Color.White,
                    shape = RoundedCornerShape(8.dp)
                )
                .wrapContentSize()
                .padding(
                    vertical = 4.dp,
                    horizontal = 8.dp
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${lang?.uppercase()}",
                fontSize = 12.sp,
                color = MaterialTheme.colors.primary,
                modifier = Modifier.wrapContentSize(),
                fontFamily = FontFamily(Font(R.font.satoshi_bold))
            )
        }
    }
}