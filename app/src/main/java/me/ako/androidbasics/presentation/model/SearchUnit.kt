package me.ako.androidbasics.presentation.model

import me.ako.androidbasics.data.model.UnitEntity

class SearchUnit(val unit: UnitEntity) : Search() {
    override fun getType(): Type {
        return Type.Unit
    }
}