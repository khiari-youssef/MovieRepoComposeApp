package com.mediaapps.movierepo.domain.entities

import java.time.LocalDateTime

data class MovieVideoDetails(
     val id : String,
     val name : String,
     val url : String,
     val lang : String,
     val size : Long,
     val type : String,
     val publishDateTime : LocalDateTime,
     val isOfficial : Boolean
)