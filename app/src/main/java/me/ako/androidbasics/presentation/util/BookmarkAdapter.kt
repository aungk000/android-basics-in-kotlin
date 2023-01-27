package me.ako.androidbasics.presentation.util

import androidx.recyclerview.widget.DiffUtil
import me.ako.androidbasics.R
import me.ako.androidbasics.data.model.PathwayEntity
import me.ako.androidbasics.databinding.ItemBookmarkBinding
import me.ako.androidbasics.domain.util.Base

class BookmarkAdapter(
    private val onItemClicked: (PathwayEntity) -> Unit,
    private val onBookmarkClicked: (PathwayEntity) -> Unit,
) : Base.ClickableListAdapter<PathwayEntity, ItemBookmarkBinding>(
    R.layout.item_bookmark,
    DiffCallback()
) {
    private class DiffCallback : DiffUtil.ItemCallback<PathwayEntity>() {
        override fun areItemsTheSame(oldItem: PathwayEntity, newItem: PathwayEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PathwayEntity, newItem: PathwayEntity): Boolean {
            return oldItem.bookmarked != newItem.bookmarked
        }
    }

    override fun onViewBind(item: PathwayEntity, binding: ItemBookmarkBinding) {
        binding.apply {
            btnBookmark.setOnClickListener {
                onBookmarkClicked(item)
            }

            pathway = item
            executePendingBindings()
        }
    }

    override fun onItemViewClicked(item: PathwayEntity, binding: ItemBookmarkBinding) {
        onItemClicked(item)
    }
}