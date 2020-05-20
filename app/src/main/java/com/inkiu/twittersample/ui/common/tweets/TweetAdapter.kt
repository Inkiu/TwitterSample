package com.inkiu.twittersample.ui.common.tweets

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.inkiu.twittersample.common.image.ImageLoader
import com.inkiu.twittersample.ui.common.model.Tweet


// TODO - click listener
class TweetAdapter(
    private val typeFactory: TweetTypeFactory,
    private val imageLoader: ImageLoader
) : PagedListAdapter<Tweet, TweetViewHolder<Tweet>>(
    TweetDiffCallback
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder<Tweet> {
        return typeFactory.holder(
            viewType,
            parent
        )
    }

    override fun getItemViewType(position: Int): Int {
        return typeFactory.type(
            getItem(position)
        )
    }

    override fun onBindViewHolder(holder: TweetViewHolder<Tweet>, position: Int) {
        getItem(position)?.let { holder.bind(it, imageLoader) }
    }
}

object TweetDiffCallback : DiffUtil.ItemCallback<Tweet>() {
    override fun areItemsTheSame(oldItem: Tweet, newItem: Tweet): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Tweet, newItem: Tweet): Boolean = oldItem == newItem
}


abstract class TweetViewHolder<in T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(item: T, imageLoader: ImageLoader)
}