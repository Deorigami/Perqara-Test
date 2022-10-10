package com.ardinata.feature_dashboard.landing.pager

import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.feature_dashboard.R
import com.ardinata.feature_dashboard.databinding.PagerGameListBinding
import com.ardinata.feature_dashboard.landing.mapper.GamesResultItemEntityMapper
import com.ardinata.feature_dashboard.landing.presenter.DashboardViewModel
import com.ardinata.service_games.domain.entity.GamesResultItemEntity
import com.ardinata.test.perqara.core.base.BaseViewBindingFragment
import com.ardinata.test.perqara.core.extension.hideKeyboard
import com.ardinata.test.perqara.core.extension.textChanges
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class PagerGameList(
    override val layout: Int = R.layout.pager_game_list
) : BaseViewBindingFragment<PagerGameListBinding>(){

    private val viewModel by activityViewModels<DashboardViewModel>()
    @Inject
    override lateinit var router: DashboardLandingContract.Router

    override fun initBinding(view: View) {
        binding = PagerGameListBinding.bind(view)
    }

    override fun didMount(view: View) {
        super.didMount(view)
        initObserver()
        setListener()
        viewModel.getPaginatedGameList()
    }

    private fun initObserver(){
        viewModel.run {
            paginatedSearchResult.observe(viewLifecycleOwner){
                setupGameCardList(it, CardListType.SEARCH)
            }
            paginatedGameResult.observe(viewLifecycleOwner){
                setupGameCardList(it, CardListType.GENERAL)
            }
            gameList.listen(viewLifecycleOwner)
            searchGame.listen(
                viewLifecycleOwner,
                onStart = this@PagerGameList::showLoading,
                onComplete = this@PagerGameList::closeLoading
            )
        }
    }

    @OptIn(FlowPreview::class)
    private fun setListener() {
        binding
            ?.textFieldView
            ?.textField
            ?.textChanges()
            ?.debounce(500)
            ?.onEach {
                viewModel.searchGames(it.toString())
                if (it.isNullOrEmpty()) setupGameCardList(getGeneralGameList(), CardListType.GENERAL)
            }
            ?.launchIn(lifecycleScope)
    }

    private fun setupGameCardList(list: List<GamesResultItemEntity>, cardListType: CardListType) {
        closeLoading()
        activity?.hideKeyboard()
        binding?.emptyState?.isVisible = list.isEmpty()
        binding?.githubUserCards?.apply {
            isVisible = list.isNotEmpty()
            items = GamesResultItemEntityMapper(requireContext()).invoke(list)
            onFinishScrolling = { when(cardListType){
                CardListType.SEARCH -> viewModel.searchGames(binding?.textFieldView?.textField?.text.toString())
                CardListType.GENERAL -> viewModel.getPaginatedGameList()
            } }
            onCardPressed = { ctx ->
                val id = list.getOrNull(ctx)?.id
                id?.let {
                    router.navigateToGameDetail(
                        requireContext(),
                        it
                    )
                }
            }
        }
    }

    private fun getGeneralGameList() = viewModel.paginatedGameResult.value ?: emptyList()

    companion object {
        enum class CardListType {
            SEARCH, GENERAL
        }
    }
}