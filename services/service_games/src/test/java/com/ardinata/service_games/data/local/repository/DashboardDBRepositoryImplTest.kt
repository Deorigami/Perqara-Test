package com.ardinata.service_games.data.local.repository

import com.ardinata.service_games.data.local.dao.FavGameDao
import com.ardinata.service_games.domain.entity.GamesResultItemEntity
import com.ardinata.service_games.domain.repository.DashboardDBRepository
import com.ardinata.test.perqara.core.model.Result
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.then
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockitoExtension::class)
internal class DashboardDBRepositoryImplTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var dbRepo : DashboardDBRepository

    @Mock
    private lateinit var favGameDao : FavGameDao

    private val gamesResultItemEntity = GamesResultItemEntity(
        "Borderlands 2",
        "2012-09-18",
        "https://media.rawg.io/media/games/588/588c6bdff3d4baf66ec36b1c05b793bf.jpg",
        4.0,
        802,
    )

    @BeforeEach
    fun setup(){
        Dispatchers.setMain(testDispatcher)
        dbRepo = DashboardDBRepositoryImpl(favGameDao)
    }

    @AfterEach
    fun teardown(){
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @Test
    fun insertGithubUser() = runTest {
        //GIVEN
        val result = Result(1L)
        given(favGameDao.insertFavGame(gamesResultItemEntity)).willReturn(1)
        //WHEN
        val actualResult = dbRepo.insertFavGame(gamesResultItemEntity)
        // THEN
        then(favGameDao).should().insertFavGame(gamesResultItemEntity)
        assertEquals(result.data, actualResult.data)
    }

    @Test
    fun getGithubUsers() = runTest {
        // GIVEN
        val result = Result(listOf(gamesResultItemEntity))
        val githubUserList = listOf(gamesResultItemEntity)
        given(favGameDao.getFavGameList()).willReturn(githubUserList)

        // WHEN
        val actualResult = dbRepo.getFavGameList()
        // THEN
        then(favGameDao).should().getFavGameList()
        assertEquals(result.data?.get(0), actualResult.data?.get(0))
    }
}