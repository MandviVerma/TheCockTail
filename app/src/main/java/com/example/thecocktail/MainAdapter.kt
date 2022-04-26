package com.example.thecocktail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainAdapter(
    private var mContext: Context?,
    private var drinksData: List<Drink>
) : RecyclerView.Adapter<MainAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ItemViewHolder(itemView)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val category = itemView.findViewById<TextView>(R.id.tvCategory)
        val image = itemView.findViewById<ImageView>(R.id.ivThumb)


    }


    override fun onBindViewHolder(holder: MainAdapter.ItemViewHolder, position: Int) {
        holder.category.text = drinksData[position].strCategory
        mContext?.let {
            Glide.with(it).load(drinksData[position].strDrinkThumb)
                .placeholder(R.drawable.ic_baseline_image).into(holder.image)
        }

    }

    override fun getItemCount(): Int {
        return drinksData.size

    }

}