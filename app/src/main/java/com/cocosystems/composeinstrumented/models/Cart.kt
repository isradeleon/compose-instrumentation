package com.cocosystems.composeinstrumented.models

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class Cart {
    private val items = mutableListOf<Product>()

    private val mTotal: MutableStateFlow<Double> = MutableStateFlow(0.0)
    val total: StateFlow<Double> = mTotal

    fun addItem(product: Product) {
        items.add(product)
        mTotal.value = items.sumOf { it.price }
    }

    fun removeItem(product: Product): Boolean {
        return if (items.contains(product)) {
            items.remove(product)
            mTotal.value = items.sumOf { it.price }
            true
        } else false
    }
}