package ru.prokhorov.cptapp

import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extras = intent.extras
        val b = extras!!.getByteArray("picture")
        val title = extras.getString("title")
        val subtitle = extras.getString("subtitle")

        val bmp = BitmapFactory.decodeByteArray(b, 0, b!!.size)
        val image = ImageView(this)
        image.setImageBitmap(bmp)

        val linearLayout = findViewById<View>(R.id.test) as LinearLayout
        val titleText = TextView(this)
        val subtitleText = TextView(this)

        linearLayout.addView(image)
        linearLayout.addView(titleText)
        linearLayout.addView(subtitleText)

        titleText.text = title
        titleText.setTextColor(Color.BLACK)
        titleText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24F)
        titleText.setPadding(40,0,0,0)

        subtitleText.text = subtitle
        subtitleText.setPadding(40,0,0,0)

        val displayMetrics = DisplayMetrics()

        windowManager.defaultDisplay.getMetrics(displayMetrics)

        val width = displayMetrics.widthPixels

        image.requestLayout()
        image.layoutParams.width = LayoutParams.MATCH_PARENT
        image.layoutParams.height = width
        image.scaleType = ImageView.ScaleType.CENTER_CROP
        val param = image.layoutParams as ViewGroup.MarginLayoutParams
        param.setMargins(0,0,0,30)
        image.layoutParams = param

        supportActionBar?.title = title
    }
}