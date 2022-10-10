package com.ardinata.test.perqara.template

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.ardinata.component.R
import com.ardinata.component.databinding.GithubUserCardBinding
import com.ardinata.test.perqara.util.customSetImage

class GamesCardView(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs){
    private val binding = GithubUserCardBinding.inflate(LayoutInflater.from(context), this, true)

    data class Data(
        val imagePoster: Any? = null,
        val title: String = "",
        val releaseDate: String,
        val ratingScore: String
    )

    var imagePoster: Any? = null
        set(value) {
            field = value
            refreshView()
        }

    var title: String = ""
        set(value) {
            field = value
            refreshView()
        }

    var releaseDate: String = ""
        set(value) {
            field = value
            refreshView()
        }

    var ratingScore: String = ""
        set(value) {
            field = value
            refreshView()
        }

    override fun setOnClickListener(l: OnClickListener?) {
        binding.root.setOnClickListener(l)
    }

    private fun refreshView(){
        binding.apply {
            leftImage.customSetImage(imagePoster)
            title.text = this@GamesCardView.title
            subtitle.text = releaseDate
            rating.text = ratingScore
        }
    }
}