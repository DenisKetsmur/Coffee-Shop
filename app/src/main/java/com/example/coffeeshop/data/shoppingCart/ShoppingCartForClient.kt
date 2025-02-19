package com.example.coffeeshop.data.shoppingCart

import com.example.coffeeshop.data.HasId
import com.example.coffeeshop.data.ItemViewModel
import com.example.coffeeshop.data.RepositoryImpl

data class CartClient(
    override val id: Int,
    val price: Double,
    var quantity: Int
):HasId

class CartClientViewModel : ItemViewModel<CartClient>(
    RepositoryImpl(emptyList())
)
