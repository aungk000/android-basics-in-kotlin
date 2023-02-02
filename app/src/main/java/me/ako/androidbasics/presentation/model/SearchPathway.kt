package me.ako.androidbasics.presentation.model

import me.ako.androidbasics.data.model.PathwayEntity

class SearchPathway(val pathway: PathwayEntity) : Search() {
    override fun getType(): Type {
        return Type.Pathway
    }
}