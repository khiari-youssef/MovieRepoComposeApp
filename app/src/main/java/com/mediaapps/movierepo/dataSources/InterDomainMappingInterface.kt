package com.mediaapps.movierepo.dataSources

interface InterDomainMappingInterface<Start,Destination> {

    fun fromStartToDestination(start: Start) : Destination
    fun fromDestinationToStart(destination: Destination) : Start

    fun fromStartToDestination(start: List<Start>) : List<Destination> = start.map {
        fromStartToDestination(it)
    }
    fun fromDestinationToStart(destination: List<Destination>) : List<Start> = destination.map {
        fromDestinationToStart(it)
    }
}