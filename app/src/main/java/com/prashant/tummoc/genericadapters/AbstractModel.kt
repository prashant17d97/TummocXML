package com.prashant.tummoc.genericadapters

import androidx.recyclerview.widget.RecyclerView
import kotlinx.parcelize.RawValue

abstract class AbstractModel {
    @Transient
    var vpPosition: Int = -1

    @Transient
    var length: Int = 0

    @Transient
    var viewHolder: RecyclerView.ViewHolder? = null

    @Transient
    var onItemClick: @RawValue RecyclerAdapter.OnItemClick? = null

    var position: Int = 0
    val lastIndex:Int
    get() = length-1
}