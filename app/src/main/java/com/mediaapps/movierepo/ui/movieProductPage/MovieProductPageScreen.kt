package com.mediaapps.movierepo.ui.movieProductPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mediaapps.movierepo.R
import com.mediaapps.movierepo.domain.entities.MovieProductDetails
import com.mediaapps.movierepo.ui.components.AdultMark
import com.mediaapps.movierepo.ui.components.MovieLangMark
import com.mediaapps.movierepo.viewModels.ProductPageViewModel
import java.time.LocalDate

@Preview
@Composable
fun MovieProductPageScreen(
   // movieProductPageViewModel: ProductPageViewModel = hiltViewModel()
) {

    val screenScrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .verticalScroll(state = screenScrollState)
            .systemBarsPadding()
            .background(Color.White)
            .fillMaxSize()
    ){
        MoviePoster(
            posterPath = "",
            isForAdult = true
        )
        MovieProductDetailsCard(
            modifier = Modifier
                .fillMaxSize()
                .offset(
                    y = (-20).dp
                )
        )
    }
}


@Composable
fun MoviePoster(
    posterPath : String,
    isForAdult : Boolean = false
) {
    ConstraintLayout(
        modifier = Modifier
            .height(250.dp)
            .fillMaxWidth()
    ){
        val (poster,adultMark) = createRefs()
        val placeholder = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.movie_item_placeholder))
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w500${posterPath}",
            contentDescription = "",
            placeholder = placeholder,
            error = placeholder,
            modifier = Modifier
                .constrainAs(poster) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                    width = Dimension.fillToConstraints
                },
            contentScale = ContentScale.Crop
        )

        AdultMark(
            isVisible = isForAdult,
            modifier = Modifier.constrainAs(adultMark){
                end.linkTo(parent.end,16.dp)
                bottom.linkTo(parent.bottom,32.dp)
            }
        )
    }
}

@Preview(widthDp = 360, heightDp = 650)
@Composable
fun MovieProductDetailsCard(
    modifier: Modifier = Modifier,
    movieProductDetails: MovieProductDetails = MovieProductDetails(
        0,"Jaws","", LocalDate.now(),"lurepsum".repeat(120),""
    )
) {
    Card(
        shape = RoundedCornerShape(
            topEnd = 15.dp,
            topStart = 15.dp
        ),
        backgroundColor = Color.White,
        modifier = modifier
    ) {
      Column(
          horizontalAlignment = Alignment.Start,
          verticalArrangement = Arrangement.spacedBy(10.dp),
          modifier = Modifier
              .fillMaxSize()
              .padding(
                  top = 16.dp
              )
              .padding(
                  horizontal = 16.dp
              )
      ) {
          Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
              Text(
                  text = movieProductDetails.title,
                  color = MaterialTheme.colors.primary,
                  fontSize = 20.sp,
                  fontFamily = FontFamily(Font(R.font.satoshi_bold))
              )
              movieProductDetails.language?.takeUnless {
                  it.isBlank()
              }?.run {
                  Text(
                      text = "(${uppercase()})",
                      color = MaterialTheme.colors.primaryVariant,
                      fontSize = 14.sp,
                      fontFamily = FontFamily(Font(R.font.satoshi_bold))
                  )
              }
          }
          Text(
              text = stringResource(id = R.string.movies_catalog_release_date_label,movieProductDetails.releaseDate.year),
              color = MaterialTheme.colors.primaryVariant,
              fontSize = 14.sp,
              fontFamily = FontFamily(Font(R.font.satoshi_bold))
          )
          Column(
              modifier = Modifier
                  .padding(
                      top = 22.dp
                  )
                  .fillMaxWidth()
          ) {
              Text(
                  text = movieProductDetails.overView,
                  color = Color.Black,
                  fontSize = 14.sp,
                  fontFamily = FontFamily(Font(R.font.satoshi_regular)),
                  lineHeight = 21.sp,
                  fontStyle = FontStyle.Italic,
                  modifier = Modifier
                      .fillMaxWidth()
                      .wrapContentHeight()
              )
          }
      }
    }
}