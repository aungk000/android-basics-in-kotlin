package me.ako.androidbasics.presentation.presenter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import me.ako.androidbasics.data.model.ActivityEntity
import me.ako.androidbasics.data.model.PathwayEntity
import me.ako.androidbasics.data.model.UnitEntity
import me.ako.androidbasics.databinding.ItemSearchBinding
import me.ako.androidbasics.databinding.ItemSearchHeaderBinding
import me.ako.androidbasics.presentation.model.*

class SearchAdapter(
    private val onUnitClicked: (UnitEntity) -> Unit,
    private val onPathwayClicked: (PathwayEntity) -> Unit,
    private val onActivityClicked: (ActivityEntity) -> Unit
) : ListAdapter<Search, ViewHolder>(DiffCallback()) {
    private var query: String = ""

    private class DiffCallback : DiffUtil.ItemCallback<Search>() {
        override fun areItemsTheSame(
            oldItem: Search,
            newItem: Search
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: Search,
            newItem: Search
        ): Boolean {
            return oldItem != newItem
        }
    }

    class HeaderViewHolder(private val binding: ItemSearchHeaderBinding) :
        ViewHolder(binding.root) {
        fun onBind(item: SearchHeader) {
            binding.apply {
                header = when (item.type) {
                    SearchHeader.Type.Unit -> {
                        item.type.value
                    }
                    SearchHeader.Type.Pathway -> {
                        item.type.value
                    }
                    SearchHeader.Type.Activity -> {
                        item.type.value
                    }
                }
                executePendingBindings()
            }
        }
    }

    class ListViewHolder(private val binding: ItemSearchBinding) : ViewHolder(binding.root) {
        fun onBind(search: Search) {
            binding.apply {
                when (search.getType()) {
                    Search.Type.Unit -> {
                        val item = search as SearchUnit
                        title = item.unit.title
                        description = item.unit.description
                    }
                    Search.Type.Pathway -> {
                        val item = search as SearchPathway
                        title = item.pathway.title
                        description = item.pathway.description
                    }
                    Search.Type.Activity -> {
                        val item = search as SearchActivity
                        title = item.activity.title
                        description = item.activity.description
                    }
                    else -> {}
                }
                executePendingBindings()
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).getType().value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val bindingHeader = ItemSearchHeaderBinding.inflate(inflater, parent, false)
        val binding = ItemSearchBinding.inflate(inflater, parent, false)

        return if (viewType == Search.Type.Header.value) {
            HeaderViewHolder(bindingHeader)
        } else {
            ListViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        when (item.getType()) {
            Search.Type.Header -> {
                (holder as HeaderViewHolder).onBind(item as SearchHeader)
            }
            Search.Type.Unit -> {
                (holder as ListViewHolder).onBind(item)
                holder.itemView.setOnClickListener {
                    onUnitClicked((item as SearchUnit).unit)
                }
            }
            Search.Type.Pathway -> {
                (holder as ListViewHolder).onBind(item)
                holder.itemView.setOnClickListener {
                    onPathwayClicked((item as SearchPathway).pathway)
                }
            }
            Search.Type.Activity -> {
                (holder as ListViewHolder).onBind(item)
                holder.itemView.setOnClickListener {
                    onActivityClicked((item as SearchActivity).activity)
                }
            }
        }
    }

    /*private fun boldQuery(raw: String): SpannableStringBuilder {
        val lowercase = raw.lowercase()
        val index = lowercase.indexOf(query)
        val startIndex = index - 15
        val endIndex = index + 15
        val bold = SpannableStringBuilder(lowercase, startIndex, endIndex).bold { query }
        return bold
    }

    fun submitQuery(query: String) {
        this.query = query.lowercase()
    }*/
}