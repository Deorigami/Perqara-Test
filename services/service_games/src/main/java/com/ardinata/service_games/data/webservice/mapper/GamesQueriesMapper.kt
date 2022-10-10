package com.ardinata.service_games.data.webservice.mapper

import com.ardinata.service_games.domain.entity.GamesQueriesEntity
import javax.inject.Inject

class GamesQueriesMapper @Inject constructor() {
    operator fun invoke(from : GamesQueriesEntity) : Map<String, String> {
        val param = mutableMapOf<String, String>()
        param["page"] = from.page
        param["page_size"] = from.pageSize
        from.search?.let { param["search"] = it }
        return param
    }
}