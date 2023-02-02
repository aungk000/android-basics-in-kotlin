package me.ako.androidbasics.presentation.presenter

import androidx.recyclerview.widget.DiffUtil
import me.ako.androidbasics.R
import me.ako.androidbasics.data.model.PathwayEntity
import me.ako.androidbasics.data.model.UnitWithPathways
import me.ako.androidbasics.databinding.ItemUnitBinding
import me.ako.androidbasics.domain.util.Base

class UnitAdapter(
    private val onItemClicked: (UnitWithPathways) -> Unit,
    private val onBadgeClicked: (PathwayEntity) -> Unit
) : Base.ClickableListAdapter<UnitWithPathways, ItemUnitBinding>(
    R.layout.item_unit,
    DiffCallback()
) {
    private class DiffCallback : DiffUtil.ItemCallback<UnitWithPathways>() {
        override fun areItemsTheSame(
            oldItem: UnitWithPathways,
            newItem: UnitWithPathways
        ): Boolean {
            return oldItem.unit.id == newItem.unit.id
        }

        override fun areContentsTheSame(
            oldItem: UnitWithPathways,
            newItem: UnitWithPathways
        ): Boolean {
            return oldItem.unit == newItem.unit
        }
    }

    override fun onViewBind(item: UnitWithPathways, binding: ItemUnitBinding) {
        binding.apply {
            val adapter = BadgeAdapter {
                onBadgeClicked(it)
            }
            recyclerViewBadges.adapter = adapter

            this.item = item
            executePendingBindings()
        }
    }

    override fun onItemViewClicked(item: UnitWithPathways, binding: ItemUnitBinding) {
        onItemClicked(item)
    }
}