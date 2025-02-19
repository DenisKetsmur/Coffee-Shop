package com.example.coffeeshop.data.shoppingCart

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.coffeeshop.data.HasId
import com.example.coffeeshop.data.ItemViewModel
import com.example.coffeeshop.data.RepositoryImpl

data class CartSupplier(
    override val id: Int,
    var quantity: Double = 1.0
): HasId

class CartSupplierViewModel : ItemViewModel<CartSupplier>(
    RepositoryImpl(emptyList())
)
