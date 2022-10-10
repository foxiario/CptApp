package ru.prokhorov.cptapp

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = title

        val extras = intent.extras
        val imageByteArray = extras!!.getByteArray("picture")
        val title = extras.getString("title")
        val subtitle = extras.getString("subtitle")

        val bmp = BitmapFactory.decodeByteArray(imageByteArray, 0, imageByteArray!!.size)
        val image = findViewById<View>(R.id.imageView_second) as ImageView
        image.setImageBitmap(bmp)

        val titleText = findViewById<View>(R.id.titleTextView) as TextView
        titleText.text = title

        val subtitleText = findViewById<View>(R.id.subtitleTextView) as TextView
        subtitleText.text = subtitle

        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        image.requestLayout()
        image.layoutParams.height = width
        val param = image.layoutParams as ViewGroup.MarginLayoutParams
        image.layoutParams = param
    }
}