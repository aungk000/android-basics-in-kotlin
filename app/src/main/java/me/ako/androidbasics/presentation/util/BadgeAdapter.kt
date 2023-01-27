package me.ako.androidbasics.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.google.android.material.snackbar.Snackbar
import me.ako.androidbasics.R
import me.ako.androidbasics.data.model.PathwayEntity
import me.ako.androidbasics.databinding.ItemBadgeBinding
import me.ako.androidbasics.domain.util.Base

class BadgeAdapter(private val onItemClicked: (PathwayEntity) -> Unit) :
    Base.ClickableListAdapter<PathwayEntity, ItemBadgeBinding>(
        R.layout.item_badge,
        DiffCallback()
    ) {
    class DiffCallback : DiffUtil.ItemCallback<PathwayEntity>() {
        override fun areItemsTheSame(oldItem: PathwayEntity, newItem: PathwayEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PathwayEntity, newItem: PathwayEntity): Boolean {
            return oldItem == newItem
        }
    }

    override fun onViewBind(item: PathwayEntity, binding: ItemBadgeBinding) {
        binding.apply {
            pathway = item
            executePendingBindings()
        }
    }

    override fun onItemViewClicked(item: PathwayEntity, binding: ItemBadgeBinding) {
        onItemClicked(item)
    }

    override fun onItemViewLongClicked(item: PathwayEntity, binding: ItemBadgeBinding): Boolean {
        Snackbar.make(binding.root, item.title, Snackbar.LENGTH_SHORT).show()
        return true
    }
}