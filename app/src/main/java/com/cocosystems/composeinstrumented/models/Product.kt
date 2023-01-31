package com.cocosystems.composeinstrumented.models

import java.util.*

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: Double
) {
    companion object {
        fun fakeData() = listOf(
            Product(
                UUID.randomUUID().toString(),
                "Pizza de pepperoni",
                "Clasica pizza de pepperoni con extra queso.",
                129.90
            ),
            Product(
                UUID.randomUUID().toString(),
                "Pizza mexicana",
                "Carne molida, pimientos y cebolla bien doradita.",
                139.90
            ),
            Product(
                UUID.randomUUID().toString(),
                "Pizza hawaiana",
                "La pizza con piña es lo mejor! #teampizzaconpiña.",
                119.90
            ),
            Product(
                UUID.randomUUID().toString(),
                "Pizza de tres carnes",
                "Disfruta de esta deliciosa pizza a un precio sin igual.",
                159.90
            ),
        )
    }
}