package ru.prokhorov.cptapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent.extras?.let {
            val title = it.getString(EXTRAS_TITLE)
            val subtitle = it.getString(EXTRAS_SUBTITLE)
            val drawableID = it.getInt(EXTRAS_DRAWABLE_ID)

            val image = findViewById<ImageView>(R.id.imageView_second)

            image.setImageResource(drawableID)

            val titleText = findViewById<TextView>(R.id.titleTextView)
            titleText.text = title

            supportActionBar?.title = title

            val subtitleText = findViewById<TextView>(R.id.subtitleTextView)
            subtitleText.text = subtitle
        }
    }
}