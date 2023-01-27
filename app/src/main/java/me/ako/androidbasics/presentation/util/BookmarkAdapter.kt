package me.ako.androidbasics.presentation.util

import androidx.recyclerview.widget.DiffUtil
import me.ako.androidbasics.R
import me.ako.androidbasics.data.model.BookmarkWithPathway
import me.ako.androidbasics.databinding.ItemBookmarkBinding
import me.ako.androidbasics.domain.util.Base

class BookmarkAdapter(
    private val onItemClicked: (BookmarkWithPathway) -> Unit,
    private val onBookmarkClicked: (BookmarkWithPathway) -> Unit,
) : Base.ClickableListAdapter<BookmarkWithPathway, ItemBookmarkBinding>(
        R.layout.item_bookmark,
        DiffCallback()
    ) {
    private class DiffCallback : DiffUtil.ItemCallback<BookmarkWithPathway>() {
        override fun areItemsTheSame(oldItem: BookmarkWithPathway, newItem: BookmarkWithPathway): Boolean {
            return oldItem.bookmark.id == newItem.bookmark.id
        }

        override fun areContentsTheSame(oldItem: BookmarkWithPathway, newItem: BookmarkWithPathway): Boolean {
            return oldItem.pathway.bookmarked != newItem.pathway.bookmarked
        }
    }

    override fun onViewBind(item: BookmarkWithPathway, binding: ItemBookmarkBinding) {
        binding.apply {
            btnBookmark.setOnClickListener {
                onBookmarkClicked(item)
            }

            bookmark = item
            executePendingBindings()
        }
    }

    override fun onItemViewClicked(item: BookmarkWithPathway, binding: ItemBookmarkBinding) {
        onItemClicked(item)
    }
}