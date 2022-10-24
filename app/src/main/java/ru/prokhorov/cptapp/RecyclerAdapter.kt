package ru.prokhorov.cptapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecyclerAdapter(
    private val items: MutableList<ItemModel>,
    private val listener: SelectListener
) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val linearLayout: LinearLayout = itemView.findViewById(R.id.itemLinearLayout)
        val imageView: ImageView = itemView.findViewById(R.id.imageView_item)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView_item)
        val subtitleTextView: TextView = itemView.findViewById(R.id.subtitleTextView_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide
            .with(holder.imageView)
            .load(items[position].imageUrl)
            .into(holder.imageView)

        holder.linearLayout.setOnClickListener {
            listener.onItemClicked(items[position])
        }

        holder.titleTextView.text = items[position].title
        holder.subtitleTextView.text = items[position].subtitle
    }

    override fun getItemCount() = items.size
}