package ru.prokhorov.cptapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toBitmapOrNull
import androidx.core.view.children
import java.io.ByteArrayOutputStream

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createItem(
            ResourcesCompat.getDrawable(resources, R.drawable.road, theme),
            "Первый",
            "Подзаголовок 1"
        )
        createItem(
            ResourcesCompat.getDrawable(resources, R.drawable.tower, theme),
            "Второй",
            "Подзаголовок 2"
        )
        createItem(
            ResourcesCompat.getDrawable(resources, R.drawable.bird, theme),
            "Третий",
            "Подзаголовок 3"
        )
        createItem(
            ResourcesCompat.getDrawable(resources, R.drawable.sky, theme),
            "Четвертый",
            "Подзаголовок 4"
        )
        createItem(
            ResourcesCompat.getDrawable(resources, R.drawable.lightning, theme),
            "Пятый",
            "Подзаголовок 5"
        )
    }

    private fun createItem(drawable: Drawable?, title: String, subtitle: String) {
        val ltInflater = layoutInflater
        val linLayout = findViewById<View>(R.id.linearLayout_main) as LinearLayout
        val itemLayout = ltInflater.inflate(R.layout.item, linLayout, false) as LinearLayout

        linLayout.addView(itemLayout)
        addItemEventListener(itemLayout)

        itemLayout.children.forEach { view ->
            if (view is ImageView) {
                view.setImageDrawable(drawable)
            }
            if (view is LinearLayout) {
                view.children.forEach { childView ->
                    if (childView is TextView) {
                        if (childView.id == R.id.titleTextView_item) childView.text = title
                        if (childView.id == R.id.subtitleTextView_item) childView.text = subtitle
                    }
                }
            }
        }
    }

    private fun addItemEventListener(itemsLayout: LinearLayout) {
        itemsLayout.setOnClickListener {
            val itemLayout = it as LinearLayout
            var image: Drawable
            val intent = Intent(this, SecondActivity::class.java)

            itemLayout.children.forEach { view ->
                if (view is ImageView) {
                    image = view.drawable
                    if (image is BitmapDrawable) {
                        val bitmapDrawable = image
                        if (bitmapDrawable.toBitmapOrNull() != null) {
                            val bitmap = bitmapDrawable.toBitmap(300, 300)
                            val baos = ByteArrayOutputStream()
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos)
                            val b: ByteArray = baos.toByteArray()
                            intent.putExtra("picture", b)
                        }
                    }
                }
                if (view is LinearLayout) {
                    view.children.forEach { view2 ->
                        if (view2 is TextView) {
                            if (view2.id == R.id.titleTextView_item) intent.putExtra(
                                "title",
                                view2.text
                            )
                            if (view2.id == R.id.subtitleTextView_item) intent.putExtra(
                                "subtitle",
                                view2.text
                            )
                        }
                    }
                }
            }
            startActivity(intent)
        }
    }
}