package com.ardinata.test.perqara.template.group.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ardinata.test.perqara.template.GamesCardView

class GithubUserListAdapter(
    private val onFavButtonPressed : (Int) -> Unit = {}
) : RecyclerView.Adapter<GithubUserListAdapter.ViewHolder>(){

    var items = listOf<GamesCardView.Data>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(
        val view: GamesCardView,
        private val onFavButtonPressed: (Int) -> Unit
    ) : RecyclerView.ViewHolder(view) {
        fun onBind(data: GamesCardView.Data, position: Int) {
            view.apply {
                imagePoster = data.imagePoster
                title = data.title
                releaseDate = data.releaseDate
                ratingScore = data.ratingScore
                setOnClickListener { onFavButtonPressed.invoke(position) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = GamesCardView(parent.context)
        val vh = ViewHolder(binding, onFavButtonPressed)
        vh.view.layoutParams = ViewGroup.LayoutParams(
            parent.layoutParams.width,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(items[position], position)
    }

    override fun getItemCount(): Int = items.size
}