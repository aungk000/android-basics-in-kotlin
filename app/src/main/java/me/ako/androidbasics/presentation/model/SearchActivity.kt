package me.ako.androidbasics.presentation.model

import me.ako.androidbasics.data.model.ActivityEntity

class SearchActivity(val activity: ActivityEntity) : Search() {
    override fun getType(): Type {
        return Type.Activity
    }
}