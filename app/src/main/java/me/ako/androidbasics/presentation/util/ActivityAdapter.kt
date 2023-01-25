package me.ako.androidbasics.presentation.util

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import me.ako.androidbasics.R
import me.ako.androidbasics.data.model.ActivityEntity
import me.ako.androidbasics.data.model.ActivityType
import me.ako.androidbasics.databinding.ItemActivityBinding
import me.ako.androidbasics.databinding.ItemActivityVideoBinding

class ActivityAdapter(
    private val onClicked: (ActivityEntity) -> Unit,
    private val onLongClicked: (ActivityEntity) -> Boolean
) : ListAdapter<ActivityEntity, ViewHolder>(
    AsyncDifferConfig.Builder(DiffCallback()).build()
) {
    private var loaded = false

    private class DiffCallback : DiffUtil.ItemCallback<ActivityEntity>() {
        override fun areItemsTheSame(oldItem: ActivityEntity, newItem: ActivityEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ActivityEntity, newItem: ActivityEntity): Boolean {
            return oldItem.finished != newItem.finished
        }
    }

    class ActivityViewHolder(private val binding: ItemActivityBinding) : ViewHolder(binding.root) {
        fun onBind(item: ActivityEntity) {
            binding.apply {
                this.item = item
                executePendingBindings()
            }
        }
    }

    class ActivityVideoViewHolder(
        private val binding: ItemActivityVideoBinding,
        private var loaded: Boolean
    ) : ViewHolder(binding.root) {
        fun onBind(item: ActivityEntity) {
            binding.apply {
                if (!loaded) {
                    loaded = true

                    imgThumbnail.load(item.thumbnail) {
                        listener(
                            onError = { request, result ->
                                imgPlay.load(R.drawable.ic_broken_image)
                                imgPlay.visibility = View.VISIBLE
                                progressThumbnail.visibility = View.GONE
                            },
                            onSuccess = { request, result ->
                                imgPlay.visibility = View.VISIBLE
                                progressThumbnail.visibility = View.GONE
                            }
                        )
                    }
                }

                this.item = item
                executePendingBindings()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val bindingActivity = ItemActivityBinding.inflate(inflater, parent, false)
        val bindingActivityVideo = ItemActivityVideoBinding.inflate(inflater, parent, false)
        return if (viewType == 2) {
            ActivityVideoViewHolder(bindingActivityVideo, loaded)
        } else {
            ActivityViewHolder(bindingActivity)
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)

        if(getItemViewType(position) == 2) {
            (holder as ActivityVideoViewHolder).onBind(item)
        } else {
            (holder as ActivityViewHolder).onBind(item)
        }

        holder.itemView.setOnClickListener {
            onClicked(item)
        }
        holder.itemView.setOnLongClickListener {
            onLongClicked(item)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position).type) {
            ActivityType.CodeLab -> {
                1
            }
            ActivityType.Video -> {
                2
            }
            ActivityType.Article -> {
                3
            }
            ActivityType.Quiz -> {
                4
            }
            else -> {
                0
            }
        }
    }
}