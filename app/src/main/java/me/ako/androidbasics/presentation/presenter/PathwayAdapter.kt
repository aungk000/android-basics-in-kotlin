package me.ako.androidbasics.presentation.presenter

import androidx.recyclerview.widget.DiffUtil
import me.ako.androidbasics.R
import me.ako.androidbasics.data.model.PathwayEntity
import me.ako.androidbasics.databinding.ItemPathwayBinding
import me.ako.androidbasics.presentation.util.Base
import org.joda.time.format.DateTimeFormat

class PathwayAdapter(private val onItemClicked: (PathwayEntity) -> Unit) :
    Base.ClickableListAdapter<PathwayEntity, ItemPathwayBinding>(
        R.layout.item_pathway,
        DiffCallback()
    ) {
    private class DiffCallback: DiffUtil.ItemCallback<PathwayEntity>() {
        override fun areItemsTheSame(oldItem: PathwayEntity, newItem: PathwayEntity): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: PathwayEntity, newItem: PathwayEntity): Boolean {
            return oldItem == newItem
        }
    }

    override fun onViewBind(item: PathwayEntity, binding: ItemPathwayBinding) {
        binding.apply {
            pathway = item

            val pathway = "Pathway ${item.number}"
            txtPathway.text = pathway

            val activities = "${item.activities} Activities"
            txtActivities.text = activities

            val datetime = DateTimeFormat.forPattern("MMMM yyyy").print(item.datetime)
            txtDatetime.text = datetime

            executePendingBindings()
        }
    }

    override fun onItemViewClicked(item: PathwayEntity, binding: ItemPathwayBinding) {
        onItemClicked(item)
    }
}