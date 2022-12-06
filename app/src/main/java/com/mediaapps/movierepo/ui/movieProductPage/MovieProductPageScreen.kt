package com.mediaapps.movierepo.ui.movieProductPage

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mediaapps.movierepo.viewModels.ProductPageViewModel

@Preview
@Composable
fun MovieProductPageScreen(
    movieProductPageViewModel: ProductPageViewModel = hiltViewModel()
) {
}