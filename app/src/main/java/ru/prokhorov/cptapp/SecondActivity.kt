package ru.prokhorov.cptapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intent.extras?.let {
            val title = it.getString(EXTRAS_TITLE)
            val subtitle = it.getString(EXTRAS_SUBTITLE)
            val imageUrl = it.getString(EXTRAS_IMAGE_URL)

            Glide
                .with(this)
                .load(imageUrl)
                .into(findViewById(R.id.imageView_second))

            val titleText = findViewById<TextView>(R.id.titleTextView)
            titleText.text = title

            supportActionBar?.title = title

            val subtitleText = findViewById<TextView>(R.id.subtitleTextView)
            subtitleText.text = subtitle
        }
    }
}