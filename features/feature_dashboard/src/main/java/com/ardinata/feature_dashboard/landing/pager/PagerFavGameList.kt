package com.ardinata.feature_dashboard.landing.pager

import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import com.ardinata.feature_dashboard.DashboardLandingContract
import com.ardinata.feature_dashboard.R
import com.ardinata.feature_dashboard.databinding.PageFavouriteGameListBinding
import com.ardinata.feature_dashboard.landing.presenter.DashboardViewModel
import com.ardinata.service_games.domain.entity.GamesResultItemEntity
import com.ardinata.test.perqara.core.base.BaseViewBindingFragment
import com.ardinata.test.perqara.core.contract.RouterContract
import com.ardinata.test.perqara.template.GamesCardView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PagerFavGameList(
    override val layout: Int = R.layout.page_favourite_game_list
) : BaseViewBindingFragment<PageFavouriteGameListBinding>(){

    private val viewModel : DashboardViewModel by activityViewModels()

    @Inject
    override lateinit var router: DashboardLandingContract.Router

    override fun initBinding(view: View) {
        binding = PageFavouriteGameListBinding.bind(view)
    }

    override fun didMount(view: View) {
        super.didMount(view)
        setObserver()
    }

    private fun setObserver() {
        viewModel.run {
            favGame.listen(
                viewLifecycleOwner,
                onSuccess = this@PagerFavGameList::setFavGameList
            )
        }
    }

    private fun setFavGameList(list: List<GamesResultItemEntity>){
        binding?.cardGameList?.apply {
            isVisible = list.isNotEmpty()
            items = list.map { GamesCardView.Data(
                title = it.name,
                releaseDate = getString(R.string.game_detail_release_date, it.released),
                ratingScore = it.rating.toString(),
                imagePoster = it.backgroundImage
            ) }.toMutableList()
            onCardPressed = { idx ->
                val id = list.getOrNull(idx)?.id
                id?.let {
                    router.navigateToGameDetail(
                        requireContext(),
                        it
                    )
                }
            }
        }
    }
}