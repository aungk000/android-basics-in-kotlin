package me.ako.androidbasics.presentation.model

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