package com.mediaapps.movierepo.domain.exceptions

open class MovieDomainException : Exception()

class MovieResourceNotFoundException : MovieDomainException()

class MovieInvalidAPIKeyException : MovieDomainException()