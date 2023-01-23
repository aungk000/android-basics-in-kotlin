package me.ako.androidbasics.presentation.util

import android.graphics.PorterDuff
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.button.MaterialButton
import me.ako.androidbasics.R
import me.ako.androidbasics.data.model.ActivityEntity
import me.ako.androidbasics.data.model.ActivityType
import me.ako.androidbasics.data.model.PathwayEntity
import me.ako.androidbasics.data.model.UnitWithPathways
import me.ako.androidbasics.domain.model.AppViewModel.Status
import org.w3c.dom.Text

@BindingAdapter("units")
fun bindUnits(recyclerView: RecyclerView, data: List<UnitWithPathways>?) {
    data?.let {
        val adapter = recyclerView.adapter as UnitAdapter
        adapter.submitList(it)
    }
}

@BindingAdapter("badges")
fun bindBadges(recyclerView: RecyclerView, data: List<PathwayEntity>?) {
    data?.let {
        val adapter = recyclerView.adapter as BadgeAdapter
        adapter.submitList(it)
    }
}

@BindingAdapter("statusUnits")
fun bindStatusUnits(progressBar: ProgressBar, status: Status) {
    when(status) {
        Status.Loading -> {
            progressBar.visibility = View.VISIBLE
        }
        Status.Done -> {
            progressBar.visibility = View.GONE
        }
        Status.Error -> {
            progressBar.visibility = View.GONE
        }
    }
}

@BindingAdapter("pathways")
fun bindPathways(recyclerView: RecyclerView, data: List<PathwayEntity>?) {
    data?.let {
        val adapter = recyclerView.adapter as PathwayAdapter
        adapter.submitList(it)
    }
}

@BindingAdapter("activities")
fun bindActivities(recyclerView: RecyclerView, data: List<ActivityEntity>?) {
    data?.let {
        val adapter = recyclerView.adapter as ActivityAdapter
        adapter.submitList(it)
    }
}

@BindingAdapter("unlocked")
fun bindBadgeUnlocked(imageView: ImageView, progress: Int?) {
    progress?.let {
        if(it < 100) {
            imageView.setColorFilter(0x76ffffff, PorterDuff.Mode.MULTIPLY)
        }
    }

    /*val colorMatrix = ColorMatrix()
    colorMatrix.setSaturation(0f)
    imageView.colorFilter = ColorMatrixColorFilter(colorMatrix)*/
}

@BindingAdapter("load")
fun bindLoadImage(imageView: ImageView, @DrawableRes image: Int?) {
    image?.let {
        imageView.load(it)
    }
}

@BindingAdapter("activityType")
fun bindActivityType(textView: TextView, type: ActivityType) {
    when(type) {
        ActivityType.CodeLab -> {
            textView.text = ActivityType.CodeLab.type
            textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_codelab,0,0,0)
        }
        ActivityType.Video -> {
            textView.text = ActivityType.Video.type
            textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_video,0,0,0)
        }
    }
}

@BindingAdapter("optional")
fun bindOptional(textView: TextView, optional: Boolean) {
    if(optional) {
        textView.visibility = View.VISIBLE
    }
    else {
        textView.visibility = View.GONE
    }
}