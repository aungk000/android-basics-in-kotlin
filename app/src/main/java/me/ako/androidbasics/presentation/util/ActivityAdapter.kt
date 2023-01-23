package me.ako.androidbasics.presentation.util

import android.view.View
import androidx.recyclerview.widget.DiffUtil
import coil.load
import me.ako.androidbasics.R
import me.ako.androidbasics.data.model.ActivityEntity
import me.ako.androidbasics.data.model.ActivityType
import me.ako.androidbasics.databinding.ItemActivityBinding
import me.ako.androidbasics.domain.util.Base

class ActivityAdapter(private val onItemClicked: (ActivityEntity) -> Unit) :
    Base.ClickableListAdapter<ActivityEntity, ItemActivityBinding>(
        R.layout.item_activity,
        DiffCallback()
    ) {
    class DiffCallback: DiffUtil.ItemCallback<ActivityEntity>() {
        override fun areItemsTheSame(oldItem: ActivityEntity, newItem: ActivityEntity): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: ActivityEntity, newItem: ActivityEntity): Boolean {
            return oldItem == newItem
        }
    }

    override fun onItemViewClicked(item: ActivityEntity, binding: ItemActivityBinding) {
        if(!item.finished) {
            binding.cardActivity.isChecked = true
        }
        onItemClicked(item)
    }

    override fun onViewBind(item: ActivityEntity, binding: ItemActivityBinding) {
        binding.apply {
            if(item.type is ActivityType.Video) {
                var videoId = item.url.substringAfter("watch?v=")
                if(item.url.contains("youtu.be")) {
                    videoId = item.url.substringAfterLast("/")
                }

                val thumbnail = "https://img.youtube.com/vi/$videoId/0.jpg"
                imgThumbnail.load(thumbnail) {
                    listener(
                        onError = { request, result ->
                            imgPlay.visibility = View.VISIBLE
                            imgPlay.load(R.drawable.ic_broken_image)
                            progressThumbnail.visibility = View.GONE
                        },
                        onSuccess = { request, result ->
                            imgPlay.visibility = View.VISIBLE
                            progressThumbnail.visibility = View.GONE
                        }
                    )
                }
            }
            else {
                imgThumbnail.visibility = View.GONE
                imgPlay.visibility = View.GONE
                progressThumbnail.visibility = View.GONE
            }

            cardActivity.isChecked = item.finished

            this.item = item
            executePendingBindings()
        }
    }
}