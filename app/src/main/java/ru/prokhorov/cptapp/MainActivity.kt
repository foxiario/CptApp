package ru.prokhorov.cptapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createItem(
            R.drawable.road, "Первый", "Подзаголовок 1"
        )
        createItem(
            R.drawable.tower, "Второй", "Подзаголовок 2"
        )
        createItem(
            R.drawable.bird, "Третий", "Подзаголовок 3"
        )
        createItem(
            R.drawable.sky, "Четвертый", "Подзаголовок 4"
        )
        createItem(
            R.drawable.lightning, "Пятый", "Подзаголовок 5"
        )
    }

    private fun createItem(drawableID: Int, title: String, subtitle: String) {
        val linLayout = findViewById<LinearLayout>(R.id.linearLayout_main)
        val itemLayout = layoutInflater.inflate(R.layout.item, linLayout, false) as LinearLayout

        linLayout.addView(itemLayout)

        itemLayout.apply {
            findViewById<ImageView>(R.id.imageView_item).setImageResource(drawableID)
            findViewById<TextView>(R.id.titleTextView_item).text = title
            findViewById<TextView>(R.id.subtitleTextView_item).text = subtitle
        }

        itemLayout.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java).apply{
                putExtra(EXTRAS_DRAWABLE_ID, drawableID)
                putExtra(EXTRAS_TITLE, title)
                putExtra(EXTRAS_SUBTITLE, subtitle)
            }

            startActivity(intent)
        }
    }
}