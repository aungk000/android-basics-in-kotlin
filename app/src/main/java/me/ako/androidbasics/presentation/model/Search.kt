package me.ako.androidbasics.presentation.model

import me.ako.androidbasics.data.model.ActivityEntity
import me.ako.androidbasics.data.model.PathwayEntity
import me.ako.androidbasics.data.model.UnitEntity

abstract class Search {
    abstract fun getType(): Type
    sealed class Type(val value: Int) {
        object Header: Type(0)
        object Unit: Type(1)
        object Pathway: Type(2)
        object Activity: Type(3)
    }
}

class SearchHeader(val type: Type) : Search() {
    override fun getType(): Search.Type {
        return Search.Type.Header
    }

    sealed class Type(val value: String) {
        object Unit: Type("Units")
        object Pathway: Type("Pathways")
        object Activity: Type("Activities")
    }
}

class SearchUnit(val unit: UnitEntity) : Search() {
    override fun getType(): Type {
        return Type.Unit
    }
}

class SearchPathway(val pathway: PathwayEntity) : Search() {
    override fun getType(): Type {
        return Type.Pathway
    }
}

class SearchActivity(val activity: ActivityEntity) : Search() {
    override fun getType(): Type {
        return Type.Activity
    }
}