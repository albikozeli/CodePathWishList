package com.example.codepathwishlist

class WishListFetcher {

    fun getNextItem(item:String,price:String,store:String):MutableList<WishList>{
        var newItem:MutableList<WishList> = ArrayList()
        val newWishListItem = WishList(item,price, store)
        return newItem
    }
}