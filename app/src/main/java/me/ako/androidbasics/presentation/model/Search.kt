package me.ako.androidbasics.presentation.model

abstract class Search {
    abstract fun getType(): Type
    sealed class Type(val value: Int) {
        object Header: Type(0)
        object Unit: Type(1)
        object Pathway: Type(2)
        object Activity: Type(3)
    }
}