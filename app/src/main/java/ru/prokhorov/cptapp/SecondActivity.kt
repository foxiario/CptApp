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
        supportActionBar?.title = title

        val extras = intent.extras

        val title = extras?.getString(EXTRAS_TITLE)
        val subtitle = extras?.getString(EXTRAS_SUBTITLE)
        val drawableID = extras?.getInt(EXTRAS_DRAWABLE_ID)

        val image = findViewById<ImageView>(R.id.imageView_second)

        if (drawableID != null) {
            image.setImageResource(drawableID)
        }

        val titleText = findViewById<TextView>(R.id.titleTextView)
        titleText.text = title

        val subtitleText = findViewById<TextView>(R.id.subtitleTextView)
        subtitleText.text = subtitle
    }
}