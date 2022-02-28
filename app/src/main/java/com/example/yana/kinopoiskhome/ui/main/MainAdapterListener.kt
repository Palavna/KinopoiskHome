package com.example.yana.kinopoiskhome.ui.main

import com.example.yana.kinopoiskhome.data.model.Films
import com.example.yana.kinopoiskhome.data.model.Films100
import com.google.android.material.imageview.ShapeableImageView

interface MainAdapterListener {
     fun clickTop100(item: Films100, view: ShapeableImageView)

     fun clickTop250(item: Films, view: ShapeableImageView)
}