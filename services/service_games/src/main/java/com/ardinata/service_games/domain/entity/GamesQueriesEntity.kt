package com.ardinata.service_games.domain.entity

data class GamesQueriesEntity(
    val page: String,
    val pageSize: String = "15", // setting default page size per load .. but you can change later on if its required for changes
    val search: String? = null
)