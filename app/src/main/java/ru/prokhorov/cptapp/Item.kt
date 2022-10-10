package ru.prokhorov.cptapp

import android.graphics.drawable.Drawable
import android.os.Parcel
import android.os.Parcelable

class Item constructor(image : Drawable?, title : String?, subtitle : String?, description : String?)  {
    private val _image = image
    private val _title = title
    private val _subtitle = subtitle
    private val _description = description


    fun getImage(): Drawable? {
        return _image
    }

    fun getTitle(): String? {
        return _title
    }

    fun getSubtitle(): String? {
        return _subtitle
    }

    fun getDescription(): String?
    {
        return _description
    }
}