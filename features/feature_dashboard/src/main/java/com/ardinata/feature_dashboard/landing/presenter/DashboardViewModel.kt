package com.ardinata.feature_dashboard.landing.presenter

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.ardinata.service_games.domain.entity.GamesQueriesEntity
import com.ardinata.service_games.domain.entity.GamesResultItemEntity
import com.ardinata.service_games.domain.usecase.GamesListUseCase
import com.ardinata.service_games.domain.usecase.GetFavGameListUseCase
import com.ardinata.service_games.domain.usecase.SearchGamesUseCase
import com.ardinata.test.perqara.core.base.BaseViewModel
import com.ardinata.test.perqara.core.extension.StatefulLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    gamesListUseCase: GamesListUseCase,
    searchGamesUseCase: SearchGamesUseCase,
    getFavGameListUseCase: GetFavGameListUseCase
) : BaseViewModel() {
    override fun getKillableStatefulLiveData(): List<StatefulLiveData<*, *>> {
        return listOf(
            gameList,
        )
    }

    // Paginated List
    private var loadedPage = 1

    // Pagination SearchList
    private var searchPage = 1
    private var currentSearchQue = ""
    private var newSearchQue = false

    val favGame = StatefulLiveData(
        getFavGameListUseCase,
        viewModelScope
    )

    val gameList = StatefulLiveData(
        gamesListUseCase,
        viewModelScope
    )

    val searchGame = StatefulLiveData(
        searchGamesUseCase,
        viewModelScope
    )

    val paginatedSearchResult = MediatorLiveData<List<GamesResultItemEntity>>().apply {
        fun update() {
            val searchedGames = searchGame.onSuccess.value ?: emptyList()
            val newSearchedGameList = object : ArrayList<GamesResultItemEntity>() {
                init {
                    if (!newSearchQue) addAll(value?.toMutableList() ?: emptyList())
                    addAll(searchedGames)
                }
            }
            value = newSearchedGameList.distinctBy { it.id }
        }
        addSource(searchGame.onSuccess) { update() }
    }
    val paginatedGameResult = MediatorLiveData<List<GamesResultItemEntity>>().apply {
        fun update() {
            val searchedGames = gameList.onSuccess.value ?: emptyList()
            val newSearchedGameList = object : ArrayList<GamesResultItemEntity>() {
                init {
                    addAll(value?.toMutableList() ?: emptyList())
                    addAll(searchedGames)
                }
            }
            value = newSearchedGameList.distinctBy { it.id }
        }
        addSource(gameList.onSuccess) { update() }
    }

    fun searchGames(name: String) {
        if (name != currentSearchQue) {
            searchPage = 1
            currentSearchQue = name
            newSearchQue = true
        } else {
            newSearchQue = false
        }

        if (name.isNotEmpty()) {
            searchGame.execute(
                GamesQueriesEntity(
                    search = name,
                    page = searchPage.toString()
                )
            )
        }

        searchPage++
    }

    fun getPaginatedGameList() {
        gameList.getData(
            GamesQueriesEntity(page = loadedPage.toString())
        )

        loadedPage++
    }
}