package com.example.yana.kinopoiskhome.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class Decorator: RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = 20
        outRect.right = 20
        outRect.top = 20
        outRect.bottom = 20
    }
}