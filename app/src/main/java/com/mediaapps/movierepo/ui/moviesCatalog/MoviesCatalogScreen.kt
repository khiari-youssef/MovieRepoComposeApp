package com.mediaapps.movierepo.ui.moviesCatalog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mediaapps.movierepo.R
import com.mediaapps.movierepo.domain.entities.MovieCatalog
import com.mediaapps.movierepo.domain.entities.MovieItem
import com.mediaapps.movierepo.domain.states.MovieCatalogDataState
import com.mediaapps.movierepo.ui.collectAsStateLifecycleAware
import com.mediaapps.movierepo.ui.components.AdultMark
import com.mediaapps.movierepo.ui.components.EmptyListState
import com.mediaapps.movierepo.ui.components.MovieLangMark
import com.mediaapps.movierepo.viewModels.MoviesCatalogViewModel
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.util.*

@Composable
fun  MoviesCatalogScreen(
    viewModel: MoviesCatalogViewModel = hiltViewModel(),
    uiStateHolder : MoviesCatalogUIStateHolder = MoviesCatalogUIStateHolder.rememberMoviesCatalogUIState(
        viewModel = viewModel
    ),
    onMovieClicked: (movie: MovieItem) -> Unit
) {

   Column(
       modifier = Modifier
           .fillMaxSize()
           .systemBarsPadding(),
       verticalArrangement = Arrangement.Top,
       horizontalAlignment = Alignment.CenterHorizontally
   ) {
       viewModel.refreshMovieCatalog()
      val currentState = uiStateHolder.moviesCatalog.value.movieCatalog
       Box(modifier = Modifier
           .fillMaxSize(),
           contentAlignment = Alignment.Center
       ){
           when(currentState){
               is MovieCatalogDataState.Loading ->{
                   CircularProgressIndicator(
                       color = MaterialTheme.colors.primary
                   )
               }
               is MovieCatalogDataState.Error ->{
                   ErrorState()
               }
               is MovieCatalogDataState.Success->{
                   when (currentState){
                       is MovieCatalogDataState.Success.Empty ->{
                           EmptyListState(
                               messageRes = R.string.movies_catalog_list_empty
                           )
                       }
                       is MovieCatalogDataState.Success.HasResult->{
                           MoviesListColumn(
                               movieCatalog = currentState.movieCatalog ,
                               onMovieClicked = onMovieClicked
                           )
                       }
                   }
               }
           }
       }
   }

}




@Composable
fun ErrorState() {
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(text = "Error")
    }
}

@Composable
fun MoviesListColumn(
    movieCatalog: MovieCatalog,
    onMovieClicked: (movie : MovieItem)->Unit
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(
                16.dp
            )
            .fillMaxSize()
    ){
        items(movieCatalog.movies){ movie->
            MovieItem(
                movie,
                onMovieClicked = onMovieClicked
            )
        }
    }
}

@Preview(widthDp = 336, heightDp = 255)
@Composable
fun MovieItem(
    movieItem: MovieItem = MovieItem(
        0,
        "Jaws",
        "",
        LocalDate.now()
    ),
    onMovieClicked: (movie : MovieItem)->Unit= {}
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color(0xFFF2F2F2),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                onMovieClicked(movieItem)
            }
    ) {
         Column(
             verticalArrangement = Arrangement.spacedBy(8.dp),
             modifier = Modifier
                 .padding(
                     8.dp
                 )
                 .fillMaxWidth()
                 .wrapContentHeight()

         ) {
             Column(
                 verticalArrangement = Arrangement.spacedBy(8.dp),
                 horizontalAlignment = Alignment.Start
             ) {
                /* Text(
                     text = "Horror",
                     color = MaterialTheme.colors.primaryVariant,
                     fontSize = 14.sp,
                     fontFamily = FontFamily(Font(com.mediaapps.movierepo.R.font.satoshi_bold))
                 )*/
                 Text(
                     text = movieItem.title,
                     color = MaterialTheme.colors.primary,
                     fontSize = 14.sp,
                     fontFamily = FontFamily(Font(com.mediaapps.movierepo.R.font.satoshi_bold))
                 )
             }
             val placeholder  = painterResource(id = com.mediaapps.movierepo.R.drawable.movie_item_placeholder)


             Box(
                 modifier = Modifier
                     .fillMaxWidth()
                     .height(160.dp)
             ) {

                 val showMovieGradientFilter = remember {
                     mutableStateOf(false)
                 }

                 AsyncImage(
                     model = "https://image.tmdb.org/t/p/w500${movieItem.poster}"  ,
                     contentDescription = null,
                     contentScale = ContentScale.Crop,
                     onError = {
                         showMovieGradientFilter.value = false
                     },
                     onLoading = {

                     },
                     onSuccess = {
                         showMovieGradientFilter.value = true
                     },
                     modifier = Modifier
                         .align(Alignment.Center)
                         .fillMaxSize()
                         .clip(RoundedCornerShape(10.dp)),
                     placeholder = placeholder,
                     error = placeholder
                 )
                 androidx.compose.animation.AnimatedVisibility(
                     visible = showMovieGradientFilter.value,
                     enter = slideInVertically(
                         animationSpec = tween(800),
                         initialOffsetY = {
                             it/2
                         }
                     ),
                     modifier = Modifier
                         .align(Alignment.BottomCenter)
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
//                         AdultMark(
//                             isVisible = true,
//                             modifier = Modifier.constrainAs(adultMark) {
//                                 start.linkTo(parent.start,16.dp)
//                                 top.linkTo(parent.top,8.dp)
//                                 bottom.linkTo(parent.bottom,8.dp)
//                                 end.linkTo(lguidline)
//                             }
//                         )
                         Text(
                             text = stringResource(id = R.string.movies_catalog_release_date_label,movieItem.releaseDate.year) ,
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
//                         MovieLangMark(
//                             lang = movieItem.language,
//                             modifier = Modifier.constrainAs(languageSticker) {
//                                 end.linkTo(parent.end,16.dp)
//                                 top.linkTo(parent.top,8.dp)
//                                 bottom.linkTo(parent.bottom,8.dp)
//                                 start.linkTo(rguidline)
//                             }
//                         )
                     }
                 }

             }
         }
    }
}


