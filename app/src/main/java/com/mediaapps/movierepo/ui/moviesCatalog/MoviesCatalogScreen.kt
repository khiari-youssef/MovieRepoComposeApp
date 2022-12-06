package com.mediaapps.movierepo.ui.moviesCatalog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mediaapps.movierepo.domain.entities.MovieCatalog
import com.mediaapps.movierepo.domain.entities.MovieItem
import com.mediaapps.movierepo.domain.states.MovieCatalogDataState
import com.mediaapps.movierepo.ui.collectAsStateLifecycleAware
import com.mediaapps.movierepo.ui.components.EmptyListState
import com.mediaapps.movierepo.viewModels.MoviesCatalogViewModel
import kotlinx.coroutines.flow.map
import java.time.LocalDate

@Composable
fun  MoviesCatalogScreen(
    viewModel: MoviesCatalogViewModel = hiltViewModel(),
    uiStateHolder : MoviesCatalogUIStateHolder = MoviesCatalogUIStateHolder.rememberMoviesCatalogUIState(
        viewModel = viewModel
    )
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
                               messageRes = com.mediaapps.movierepo.R.string.movies_catalog_list_empty
                           )
                       }
                       is MovieCatalogDataState.Success.HasResult->{
                           MoviesListColumn(
                               movieCatalog = currentState.movieCatalog ,
                               onMovieClicked =  remember {
                                   {

                                   }
                               })
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
                 Text(
                     text = "Horror",
                     color = MaterialTheme.colors.primaryVariant,
                     fontSize = 14.sp,
                     fontFamily = FontFamily(Font(com.mediaapps.movierepo.R.font.satoshi_bold))
                 )
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
                 AsyncImage(
                     model = "https://image.tmdb.org/t/p/w500${movieItem.poster}"  ,
                     contentDescription = null,
                     contentScale = ContentScale.Crop,
                     modifier = Modifier
                         .align(Alignment.Center)
                         .fillMaxSize()
                         .clip(RoundedCornerShape(10.dp)),
                     placeholder = placeholder,
                     error = placeholder
                 )
                 ConstraintLayout(
                     modifier = Modifier
                         .align(Alignment.BottomCenter)
                         .fillMaxHeight(0.3f)
                         .fillMaxWidth()
                         .clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp))
                         .background(brush = Brush.verticalGradient(listOf(
                             Color.Transparent,
                             Color(0x66000000),
                             Color(0x99000000),
                             Color(0x99000000),
                             Color(0x99000000),
                             Color(0x99000000)
                         )))
                 ) {

                 }
             }
         }
    }
}