package com.wayyan.countriessqldelight.base.core

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewHolder<Model>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(model: Model)
}