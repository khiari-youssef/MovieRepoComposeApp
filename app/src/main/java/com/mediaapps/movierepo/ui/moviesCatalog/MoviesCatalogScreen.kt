package com.mediaapps.movierepo.ui.moviesCatalog

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mediaapps.movierepo.domain.entities.MovieCatalog
import com.mediaapps.movierepo.domain.entities.MovieItem
import com.mediaapps.movierepo.domain.states.MovieCatalogDataState
import com.mediaapps.movierepo.ui.collectAsStateLifecycleAware
import com.mediaapps.movierepo.viewModels.MoviesCatalogViewModel
import kotlinx.coroutines.flow.map

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
                   Text(text = "Loading")
               }
               is MovieCatalogDataState.Error ->{
                   ErrorState()
               }
               is MovieCatalogDataState.Success->{
                   when (currentState){
                       is MovieCatalogDataState.Success.Empty ->{
                           EmptyListState()
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
fun EmptyListState() {
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
             val lottieAnime  by rememberLottieComposition(LottieCompositionSpec.RawRes(com.mediaapps.movierepo.R.raw.empty_list_lottie))
             LottieAnimation(
                 composition = lottieAnime,
                 modifier = Modifier.size(300.dp)
             )
             Text(
                 text = "Movies unavailable !",
                 textAlign = TextAlign.Center,
                 modifier = Modifier.fillMaxWidth(),
                 fontSize = 20.sp,
                 fontWeight = FontWeight(700),
                 lineHeight = 41.sp,
                 color = Color.Blue
             )
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
                horizontal = 16.dp
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


@Composable
fun MovieItem(
    movieItem: MovieItem,
    onMovieClicked: (movie : MovieItem)->Unit
) {

}