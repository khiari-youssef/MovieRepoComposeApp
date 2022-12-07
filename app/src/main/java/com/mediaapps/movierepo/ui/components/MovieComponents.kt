package com.mediaapps.movierepo.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
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


@Composable
fun MovieGradientFilterRow(
    isForAdults : Boolean,
    lang : String?,
    releaseYear : Int
) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp))
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            Color(0x66000000),
                            Color(0x99000000),
                            Color(0x99000000),
                            Color(0x99000000),
                            Color(0x99000000)
                        )
                    )
                )
        ) {
            val (adultMark,releaseDate,languageSticker) = createRefs()
            val lguidline = createGuidelineFromStart(0.2f)
            val rguidline = createGuidelineFromEnd(0.2f)
            AdultMark(
                isVisible = isForAdults,
                modifier = Modifier.constrainAs(adultMark) {
                    start.linkTo(parent.start,8.dp)
                    top.linkTo(releaseDate.top)
                    bottom.linkTo(releaseDate.bottom)
                    end.linkTo(lguidline)
                }
            )
            Text(
                text = stringResource(id = R.string.movies_catalog_release_date_label,releaseYear) ,
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier
                    .constrainAs(releaseDate){
                        start.linkTo(lguidline,16.dp)
                        end.linkTo(rguidline,16.dp)
                        bottom.linkTo(parent.bottom,8.dp)
                        width = Dimension.fillToConstraints
                    },
                fontFamily = FontFamily(Font(R.font.satoshi_bold)),
                textAlign = TextAlign.Center
            )
            MovieLangMark(
                lang = lang,
                modifier = Modifier.constrainAs(languageSticker) {
                    end.linkTo(parent.end,8.dp)
                    top.linkTo(releaseDate.top)
                    bottom.linkTo(releaseDate.bottom)
                    start.linkTo(rguidline)
                }
            )
    }
}