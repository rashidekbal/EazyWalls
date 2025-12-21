package com.rtech.eazywalls.utils

import android.widget.ImageView
import coil3.load

class CoilUtil {
    companion object{
        @JvmStatic
        fun loadImage(imageView: ImageView,url:String){
            imageView.load(url)
        }
    }
}