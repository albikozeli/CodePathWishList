package com.example.codepathwishlist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    val newWishListItem:MutableList<WishList> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val wishadapter = WishListAdapter(newWishListItem)

        val itemRv = findViewById<RecyclerView>(R.id.recyclerView)
        val submit = findViewById<Button>(R.id.button)
        val itemEt = findViewById<EditText>(R.id.item)
        val priceEt = findViewById<EditText>(R.id.price)
        val storeEt = findViewById<EditText>(R.id.store)
        val totalTv = findViewById<TextView>(R.id.total)

        val dec = DecimalFormat("#.00")
        var cost = 0.0

        submit.setOnClickListener {
            it.hideKeyboard()
            val item = itemEt.getText().toString()
            val store = storeEt.getText().toString()
            val price = priceEt.getText().toString().toFloat()

            cost +=price
            val decPrice = dec.format(price)
            val decCost = dec.format(cost)

            itemEt.text.clear()
            priceEt.text.clear()
            storeEt.text.clear()
            totalTv.text = decCost.toString()

            newWishListItem.add(WishList(item, decPrice.toString(), store))
            wishadapter.notifyItemInserted(wishadapter.itemCount-1)
            itemRv.adapter = wishadapter
            itemRv.layoutManager = LinearLayoutManager(this)

        }

        ItemClickSupport.addTo(itemRv).setOnItemLongClickListener { recyclerView, position, v ->
            // do stuff
            val itemToRemove= newWishListItem[position]
            cost-= itemToRemove.price.toFloat()
            val decCost = dec.format(cost)
            totalTv.text = decCost.toString()

            newWishListItem.removeAt(position)
            wishadapter.notifyItemRemoved(position)
            Toast.makeText(this, "Item Deleted", Toast.LENGTH_SHORT).show()
            true
        }

    }


    fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }
}