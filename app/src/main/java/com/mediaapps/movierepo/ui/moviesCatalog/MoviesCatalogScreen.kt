package com.mediaapps.movierepo.ui.moviesCatalog

import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mediaapps.movierepo.R
import com.mediaapps.movierepo.domain.entities.MovieCatalog
import com.mediaapps.movierepo.domain.entities.MovieItem
import com.mediaapps.movierepo.domain.exceptions.MovieInvalidAPIKeyException
import com.mediaapps.movierepo.domain.exceptions.MovieResourceNotFoundException
import com.mediaapps.movierepo.domain.states.MovieCatalogDataState
import com.mediaapps.movierepo.ui.components.*
import com.mediaapps.movierepo.viewModels.MoviesCatalogViewModel
import com.valentinilk.shimmer.*

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
                   LoadingMovieItems()
               }
               is MovieCatalogDataState.Error ->{
                   ErrorScreenBox(
                       messageRes = when (currentState.movieDomainException) {
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
                               baseUrl = remember {
                                   uiStateHolder.baseUrlState.value
                               },
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
fun MoviesListColumn(
    movieCatalog: MovieCatalog,
    baseUrl : String?,
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
                baseUrl = baseUrl,
                onMovieClicked = onMovieClicked
            )
        }
    }
}




@Composable
fun LoadingMovieItems() {
    val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.View)
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(
                16.dp
            )
            .fillMaxSize()
    ){
        items(10){ index->
            Box(
                modifier = Modifier
                    .background(
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFFF2F2F2)
                    )
                    .fillMaxWidth()
                    .height(200.dp)
                    .shimmer(shimmerInstance)
            )
        }
    }
}



@Composable
fun MovieItem(
    movieItem: MovieItem,
    baseUrl : String?,
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
                     text = movieItem.title,
                     color = MaterialTheme.colors.primary,
                     fontSize = 14.sp,
                     fontFamily = FontFamily(Font(R.font.satoshi_bold))
                 )
             }
             val placeholder  = painterResource(id = R.drawable.movie_item_placeholder)


             Box(
                 modifier = Modifier
                     .fillMaxWidth()
                     .height(160.dp)
             ) {

                 val showMovieGradientFilter = remember {
                     mutableStateOf(false)
                 }

                 AsyncImage(
                     model = "${baseUrl}w500${movieItem.poster}"  ,
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
                     MovieGradientFilterRow(
                         isForAdults = movieItem.isForAdult,
                         releaseYear = movieItem.releaseDate.year,
                         lang = movieItem.language
                     )
                 }


             }
         }
    }
}


