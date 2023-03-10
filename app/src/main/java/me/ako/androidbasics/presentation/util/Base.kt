package me.ako.androidbasics.presentation.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class Base {
    abstract class ClickableListAdapter<T, VB : ViewDataBinding>(
        @LayoutRes val layoutId: Int, diffCallback: DiffUtil.ItemCallback<T>
    ) : ListAdapter<T, ClickableListAdapter.ClickableViewHolder<VB>>(
        AsyncDifferConfig.Builder(diffCallback).build()
    ) {
        abstract fun onViewBind(item: T, binding: VB)
        abstract fun onItemViewClicked(item: T, binding: VB)
        open fun onItemViewLongClicked(item: T, binding: VB): Boolean {
            return false
        }

        class ClickableViewHolder<VB : ViewDataBinding>(val binding: VB) :
            RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClickableViewHolder<VB> {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate(inflater, layoutId, parent, false) as VB
            return ClickableViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ClickableViewHolder<VB>, position: Int) {
            val item = getItem(position)
            onViewBind(item, holder.binding)
            holder.itemView.setOnClickListener {
                onItemViewClicked(item, holder.binding)
            }
            holder.itemView.setOnLongClickListener {
                onItemViewLongClicked(item, holder.binding)
            }
        }
    }

    abstract class RecyclerListAdapter<T, VB : ViewDataBinding>(
        @LayoutRes val layoutId: Int, diffCallback: DiffUtil.ItemCallback<T>
    ) : ListAdapter<T, RecyclerListAdapter.RecyclerViewHolder<VB>>(diffCallback) {
        abstract fun onViewBind(item: T, binding: VB)

        class RecyclerViewHolder<VB : ViewDataBinding>(val binding: VB) :
            RecyclerView.ViewHolder(binding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder<VB> {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate(inflater, layoutId, parent, false) as VB
            return RecyclerViewHolder(binding)
        }

        override fun onBindViewHolder(holder: RecyclerViewHolder<VB>, position: Int) {
            val item = getItem(position)
            onViewBind(item, holder.binding)
        }
    }
}