package com.inkiu.twittersample.ui.common.tweets

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.inkiu.twittersample.common.image.ImageLoader
import com.inkiu.twittersample.model.Tweet
import com.inkiu.twittersample.ui.common.datasource.DataSourceState


// TODO - click listener
class TweetAdapter(
    private val imageLoader: ImageLoader,
    private val clickListener: TweetClickListener
) : PagedListAdapter<Tweet, RecyclerView.ViewHolder>(TweetDiffCallback) {

    private var networkState: DataSourceState = DataSourceState.Init

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TweetListTypeFactory.holder(viewType, parent)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? TweetViewHolder<Tweet>)?.let { tweetHolder ->
            getItem(position)?.let { tweetHolder.bind(it, clickListener, imageLoader) }
        } ?: (holder as? NetworkStateItemViewHolder)?.bind(networkState)
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1)
            TweetListTypeFactory.getLoadingType()
        else TweetListTypeFactory.type(getItem(position))
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    fun setNetworkState(newNetworkState: DataSourceState) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    private fun hasExtraRow() = networkState != DataSourceState.Success
}

object TweetDiffCallback : DiffUtil.ItemCallback<Tweet>() {
    override fun areItemsTheSame(oldItem: Tweet, newItem: Tweet): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Tweet, newItem: Tweet): Boolean = oldItem == newItem
}


abstract class TweetViewHolder<in T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(
        item: T,
        clickListener: TweetClickListener,
        imageLoader: ImageLoader
    )
}
