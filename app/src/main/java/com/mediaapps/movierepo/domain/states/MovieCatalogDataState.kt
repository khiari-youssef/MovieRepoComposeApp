package com.mediaapps.movierepo.domain.states

import com.mediaapps.movierepo.domain.entities.MovieCatalog
import com.mediaapps.movierepo.domain.exceptions.MovieDomainException

sealed interface MovieCatalogDataState {

    object Loading : MovieCatalogDataState
    data class Error(val movieDomainException: MovieDomainException) : MovieCatalogDataState
    sealed interface Success : MovieCatalogDataState {
        object Empty : Success
        data class HasResult(val movieCatalog: MovieCatalog) : Success
    }
}