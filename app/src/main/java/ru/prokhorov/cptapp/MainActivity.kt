package ru.prokhorov.cptapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
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
        val linearLayout = findViewById<View>(R.id.linearLayout_main) as LinearLayout
        val itemsLayout = LinearLayout(this)
        linearLayout.addView(itemsLayout)

        itemsLayout.orientation = LinearLayout.HORIZONTAL
        addItemEventListener(itemsLayout)

        itemsLayout.requestLayout()
        val params = itemsLayout.layoutParams
        params.width = LayoutParams.MATCH_PARENT
        params.height = 150
        itemsLayout.layoutParams = params
        val param = params as ViewGroup.MarginLayoutParams
        param.setMargins(0, 0, 0, 30)

        val itemImage = ImageView(this)
        itemsLayout.addView(itemImage)

        itemImage.requestLayout()
        itemImage.setImageDrawable(drawable)
        itemImage.layoutParams.width = 150
        itemImage.layoutParams.height = 150
        itemImage.scaleType = ImageView.ScaleType.CENTER_CROP

        val textLayout = LinearLayout(this)
        textLayout.orientation = LinearLayout.VERTICAL
        itemsLayout.addView(textLayout)
        textLayout.setPadding(30, 0, 0, 0)

        val itemTitle = TextView(this)
        textLayout.addView(itemTitle)
        itemTitle.text = title
        itemTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24F)
        itemTitle.setTextColor(Color.BLACK)

        val itemSubtitle = TextView(this)
        textLayout.addView(itemSubtitle)
        itemSubtitle.text = subtitle
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