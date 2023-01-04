package com.mediaapps.movierepo.ui.movieProductPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
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
import com.mediaapps.movierepo.domain.exceptions.MovieInvalidAPIKeyException
import com.mediaapps.movierepo.domain.exceptions.MovieResourceNotFoundException
import com.mediaapps.movierepo.domain.states.MovieProductDetailsDataState
import com.mediaapps.movierepo.ui.components.AdultMark
import com.mediaapps.movierepo.ui.components.ErrorScreenBox
import com.mediaapps.movierepo.ui.components.VideoPlayer
import com.mediaapps.movierepo.viewModels.ProductPageViewModel
import java.time.LocalDate

@Preview
@Composable
fun MovieProductPageScreen(
    viewModel: ProductPageViewModel = hiltViewModel(),
    uiStateHolder: MovieProductDetailsUIStateHolder = MovieProductDetailsUIStateHolder.rememberMovieProductDetailsUIState(
        viewModel = viewModel
    ),
    movieID : Int = -1
) {


    viewModel.fetchMovieProductDetail(
        movieID
    )

    Column(
        modifier = Modifier
            .background(
            MaterialTheme.colors.background
        )
    ) {
        when (val currentState = uiStateHolder.movieProductPage.value){
            is MovieProductDetailsDataState.Loading->{
                Box(modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center){
                    CircularProgressIndicator(
                        color = MaterialTheme.colors.primary
                    )
                }
            }
            is MovieProductDetailsDataState.Error -> {
                ErrorScreenBox(
                    when (currentState.movieDomainException) {
                        is MovieResourceNotFoundException -> {
                            R.string.error_message_not_found
                        }
                        is MovieInvalidAPIKeyException -> {
                            R.string.error_message_unauthorized
                        }
                        else -> R.string.error_message_any_error
                    }
                )
            }
            is MovieProductDetailsDataState.Success ->{
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = MaterialTheme.colors.background
                    )) {
                    ProductDetailsContent(
                        currentState.movieProductDetails,
                        baseUrl = uiStateHolder.baseUrlState.value
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ProductDetailsContent(
    movieProductDetails : MovieProductDetails = MovieProductDetails(
        0,"Jaws","", LocalDate.now(),"lurepsum".repeat(120),""
    ),
    baseUrl : String?=null
) {
    val screenScrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = screenScrollState)
            .systemBarsPadding()
    ){
        MoviePoster(
            posterPath = "${baseUrl}w500${movieProductDetails.poster}" ,
            isForAdult = movieProductDetails.isForAdult
        )
        MovieProductDetailsCard(
            modifier = Modifier
                .fillMaxSize()
                .heightIn(min = 600.dp)
                .offset(
                    y = (-20).dp
                ),
            movieProductDetails = movieProductDetails
        )

    }
}


@Composable
fun MoviePoster(
    modifier: Modifier = Modifier,
    posterPath : String,
    isForAdult : Boolean = false
) {
    ConstraintLayout(
        modifier = modifier
            .height(250.dp)
            .fillMaxWidth()
    ){
        val (poster,adultMark) = createRefs()
        val placeholder = rememberVectorPainter(image = ImageVector.vectorResource(id = R.drawable.movie_item_placeholder))
        AsyncImage(
            model = posterPath,
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
        backgroundColor = MaterialTheme.colors.surface,
        modifier = modifier
            .fillMaxSize(),
        elevation = 0.dp
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
              AdultMark(
                  isVisible = movieProductDetails.isForAdult
              )
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
                  .fillMaxSize()
          ) {
              Text(
                  text = movieProductDetails.overView,
                  color = MaterialTheme.colors.onBackground,
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