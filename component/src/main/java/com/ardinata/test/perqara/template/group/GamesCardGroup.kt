package com.ardinata.test.perqara.template.group

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ardinata.component.databinding.GroupBaseLayoutBinding
import com.ardinata.test.perqara.template.GamesCardView
import com.ardinata.test.perqara.template.group.adapter.GithubUserListAdapter

class GamesCardGroup(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    private val binding = GroupBaseLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    private val adapter by lazy {
        GithubUserListAdapter {
            onCardPressed.invoke(it)
        }
    }

    var items = mutableListOf<GamesCardView.Data>()
        set(value){
            field = value
            adapter.items = value
        }

    var onFinishScrolling : () -> Unit = {}
        set(value){
            field = value
            refreshView()
        }

    var onCardPressed : (Int) -> Unit = {}
        set(value){
            field = value
            refreshView()
        }



    init {
        clipChildren = false
        clipToPadding = false

        binding.rv.apply {
            val paddingHorizontal = this@GamesCardGroup.paddingStart
            updatePadding(left = paddingHorizontal, right = paddingHorizontal, top = paddingHorizontal, bottom = paddingHorizontal)
            adapter = this@GamesCardGroup.adapter.also { items = this@GamesCardGroup.items }
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            requestDisallowInterceptTouchEvent(false)
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(rv: RecyclerView, newState: Int) {
                    if (!rv.canScrollVertically(1) && newState == 0){
                        onFinishScrolling.invoke()
                    }
                }
            })
            itemAnimator = null
        }

        // Setting padding to 0 then set padding to the rv to prevent shadow issues on new android 13 when the item strecth like ios
        setPadding(0,0,0,0)
    }

    private fun refreshView() {

    }
}