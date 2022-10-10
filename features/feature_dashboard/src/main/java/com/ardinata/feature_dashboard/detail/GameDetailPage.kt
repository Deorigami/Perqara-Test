package com.ardinata.feature_dashboard.detail

import android.view.View
import androidx.core.text.HtmlCompat
import androidx.fragment.app.viewModels
import com.ardinata.feature_dashboard.R
import com.ardinata.feature_dashboard.databinding.PageGameDetailBinding
import com.ardinata.feature_dashboard.detail.presenter.GameDetailViewModel
import com.ardinata.service_games.domain.entity.GameDetailsEntity
import com.ardinata.service_games.domain.entity.GameDetailsEntity.Companion.toGameListItem
import com.ardinata.test.perqara.core.base.BaseViewBindingFragment
import com.ardinata.test.perqara.util.customSetImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GameDetailPage(
    override val layout: Int = R.layout.page_game_detail
) : BaseViewBindingFragment<PageGameDetailBinding>() {

    private val viewModel by viewModels<GameDetailViewModel>()

    override fun initBinding(view: View) {
        binding = PageGameDetailBinding.bind(view)
    }

    override fun didMount(view: View) {
        super.didMount(view)
        setObserver()
        initView()
        viewModel.favGameList.execute(Unit)
        viewModel.gameDetails.execute(getGameId().toString())
    }

    private fun initView() {
        binding?.simpleHeader?.apply {
            onBackPressed = { activity?.onBackPressed() }
            onFavButtonChangeListener = { isFav ->
                val gameEntity = getGameItem()
                gameEntity?.let {
                    if (isFav) viewModel.insertFavGame.execute(it)
                    else viewModel.deleteFavGame.execute(it.id)
                }
            }
        }
    }

    private fun setObserver() {
        viewModel.run {
            gameDetails.listen(
                viewLifecycleOwner,
                onStart = { showLoading() },
                onComplete = { closeLoading() },
                onSuccess = this@GameDetailPage::setGameDetails
            )
            insertFavGame.listen(viewLifecycleOwner)
            deleteFavGame.listen(viewLifecycleOwner)
            favGameList.listen(
                viewLifecycleOwner,
                onSuccess = {
                    binding?.simpleHeader?.isFav = it.any { it.id == getGameId() }
                }
            )
        }
    }

    private fun setGameDetails(detail: GameDetailsEntity) {
        binding?.poster?.customSetImage(detail.backgroundImage)
        binding?.description?.text =
            HtmlCompat.fromHtml(detail.description, HtmlCompat.FROM_HTML_MODE_LEGACY)
        binding?.publisher?.text = detail.publishers.firstOrNull()?.name
        binding?.gameTitle?.text = detail.name
        binding?.ratingScore?.text = detail.rating.toString()
        binding?.playTime?.text = getString(R.string.game_detail_played, detail.playTime)
        binding?.releaseDate?.text = getString(R.string.game_detail_release_date, detail.released)
    }

    private fun getGameId() = arguments?.getLong(GAME_ID)
    private fun getGameItem() = viewModel.gameDetails.onSuccess.value?.toGameListItem()

    companion object {
        const val GAME_ID = "GAME_ID"
        const val IS_FAV_RESULT = "IS_FAV_RESULT"
    }
}