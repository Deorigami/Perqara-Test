package com.ardinata.feature_dashboard.landing.mapper

import android.content.Context
import com.ardinata.feature_dashboard.R
import com.ardinata.service_games.domain.entity.GamesResultItemEntity
import com.ardinata.test.perqara.template.GamesCardView

class GamesResultItemEntityMapper(private val context: Context) {
    operator fun invoke(from : List<GamesResultItemEntity>) : MutableList<GamesCardView.Data> {
        return from.map { GamesCardView.Data(
            title = it.name,
            releaseDate = context.getString(R.string.game_detail_release_date, it.released),
            ratingScore = it.rating.toString(),
            imagePoster = it.backgroundImage
        ) }.toMutableList()
    }
}