package ru.prokhorov.cptapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
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

        var view: View?
        val count = itemLayout.childCount
        for (i in 0 until count) {
            view = itemLayout.getChildAt(i)
            if (view is ImageView) {
                view.setImageDrawable(drawable)
            }
            if (view is LinearLayout) {
                val count2 = view.childCount
                for (l in 0 until count2) {
                    println(view)
                    val view2 = view.getChildAt(l)
                    if (view2 is TextView) {
                        if (view2.currentTextColor == Color.BLACK) {
                            view2.text = title
                        } else {
                            view2.text = subtitle
                        }
                    }
                }
            }
        }
    }

    private fun addItemEventListener(itemsLayout: LinearLayout) {
        itemsLayout.setOnClickListener {
            val itemLayout = it as LinearLayout
            val count = itemLayout.childCount

            var view: View?
            var image: Drawable

            val intent = Intent(this, SecondActivity::class.java)

            for (i in 0 until count) {
                view = itemLayout.getChildAt(i)
                if (view is ImageView) {
                    image = view.drawable
                    if (image is BitmapDrawable) {
                        val bitmapDrawable = image
                        if (bitmapDrawable.bitmap != null) {
                            val bitmap = bitmapDrawable.bitmap
                            val baos = ByteArrayOutputStream()
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                            val b: ByteArray = baos.toByteArray()
                            intent.putExtra("picture", b)
                        }
                    }
                }
                if (view is LinearLayout) {
                    val count2 = view.childCount
                    for (l in 0 until count2) {
                        println(view)
                        val view2 = view.getChildAt(l)
                        if (view2 is TextView) {
                            if (view2.currentTextColor == Color.BLACK) {
                                intent.putExtra("title", view2.text)
                            } else {
                                intent.putExtra("subtitle", view2.text)
                            }
                        }
                    }
                }
            }

            startActivity(intent)
        }
    }
}