package com.example.codepathwishlist

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WishListAdapter(private val wishlistitems:MutableList<WishList>): RecyclerView.Adapter<WishListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTV:TextView
        val priceTV:TextView
        val storeTV:TextView

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            itemTV = itemView.findViewById(R.id.itemtextView)
            priceTV = itemView.findViewById(R.id.pricetextView)
            storeTV = itemView.findViewById(R.id.storetextView)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.wishlist_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wishlistitem = wishlistitems[position]

        holder.itemTV.text = wishlistitem.item
        holder.priceTV.text = wishlistitem.price
        holder.storeTV.text = wishlistitem.store
        holder.storeTV.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG)

    }

    override fun getItemCount(): Int {
        return wishlistitems.size
    }
}


