package ru.prokhorov.cptapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), SelectListener {
    private val items: MutableList<ItemModel> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerAdapter(initItemList(), this)
    }

    private fun initItemList(): MutableList<ItemModel> {
        (1..100).forEach { i ->
            items.add(
                ItemModel(
                    "https://picsum.photos/seed/$i/200/200",
                    "Title $i",
                    "https://picsum.photos/seed/$i/200/200"
                )
            )
        }
        return items
    }

    override fun onItemClicked(item: ItemModel) {
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra(EXTRAS_IMAGE_URL, item.imageUrl)
            putExtra(EXTRAS_TITLE, item.title)
            putExtra(EXTRAS_SUBTITLE, item.subtitle)
        }
        startActivity(intent)
    }
}