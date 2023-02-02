package me.ako.androidbasics.presentation.util

import android.graphics.PorterDuff
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.progressindicator.LinearProgressIndicator
import me.ako.androidbasics.R
import me.ako.androidbasics.data.model.ActivityEntity
import me.ako.androidbasics.data.model.ActivityType
import me.ako.androidbasics.data.model.PathwayEntity
import me.ako.androidbasics.data.model.UnitWithPathways
import me.ako.androidbasics.domain.model.AppViewModel.Status
import me.ako.androidbasics.presentation.presenter.ActivityAdapter
import me.ako.androidbasics.presentation.presenter.BadgeAdapter
import me.ako.androidbasics.presentation.presenter.PathwayAdapter
import me.ako.androidbasics.presentation.presenter.UnitAdapter

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
fun bindStatusUnits(progress: ContentLoadingProgressBar, status: Status) {
    when(status) {
        Status.Loading -> {
            progress.show()
        }
        Status.Done -> {
            progress.hide()
        }
        Status.Error -> {
            progress.hide()
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
            textView.text = type.type
            textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_codelab,0,0,0)
        }
        ActivityType.Video -> {
            textView.text = type.type
            textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_video,0,0,0)
        }
        ActivityType.Article -> {
            textView.text = type.type
            textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_article,0,0,0)
        }
        ActivityType.Quiz -> {
            textView.text = type.type
            textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_quiz,0,0,0)
        }
        else -> {

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

@BindingAdapter("progressCompat")
fun bindProgressCompat(indicator: LinearProgressIndicator, progress: Int?) {
    progress?.let {
        indicator.setProgressCompat(it, true)
    }
}

@BindingAdapter("progressCompat")
fun bindProgressCompat(indicator: CircularProgressIndicator, progress: Int?) {
    progress?.let {
        indicator.setProgressCompat(it, true)
    }
}

@BindingAdapter("app:checkedState")
fun bindCheckedState(checkBox: MaterialCheckBox, bookmarked: Boolean?) {
    bookmarked?.let {
        if(it) {
            checkBox.checkedState = MaterialCheckBox.STATE_CHECKED
        }
        else {
            checkBox.checkedState = MaterialCheckBox.STATE_UNCHECKED
        }
    }
}