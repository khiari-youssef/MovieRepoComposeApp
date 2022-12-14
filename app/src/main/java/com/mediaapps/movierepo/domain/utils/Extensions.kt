package com.mediaapps.movierepo.domain.utils

import com.mediaapps.movierepo.domain.exceptions.MovieDomainException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

fun <T> Flow<T>.reduceException(callback : (exception : MovieDomainException)->Unit) : Flow<T> = catch { exception->

    callback(if (exception is MovieDomainException) {
        exception
    }
     else {
        exception.printStackTrace()
        MovieDomainException()
    })
}