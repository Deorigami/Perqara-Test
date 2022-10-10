package com.ardinata.feature_dashboard.detail.presenter

import androidx.lifecycle.viewModelScope
import com.ardinata.service_games.domain.usecase.*
import com.ardinata.test.perqara.core.base.BaseViewModel
import com.ardinata.test.perqara.core.extension.StatefulLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    gameDetailsUseCase: GameDetailsUseCase,
    insertFavGameUseCase: InsertFavGameUseCase,
    deleteFavGameUseCase: DeleteFavGameUseCase,
    getFavGameListUseCase: GetFavGameListUseCase
) : BaseViewModel(){
    override fun getKillableStatefulLiveData(): List<StatefulLiveData<*, *>> {
        return listOf( insertFavGame,deleteFavGame, favGameList,gameDetails )
    }

    val insertFavGame = StatefulLiveData(
        insertFavGameUseCase,
        viewModelScope
    )

    val deleteFavGame = StatefulLiveData(
        deleteFavGameUseCase,
        viewModelScope
    )

    val favGameList = StatefulLiveData(
        getFavGameListUseCase,
        viewModelScope
    )

    val gameDetails = StatefulLiveData(
        gameDetailsUseCase,
        viewModelScope
    )
}