package com.cocosystems.composeinstrumented

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.cocosystems.composeinstrumented.models.Cart
import com.cocosystems.composeinstrumented.models.Product
import com.cocosystems.composeinstrumented.ui.product.MenuScreen
import com.cocosystems.composeinstrumented.ui.theme.ComposeInstrumentedTheme

class MainActivity : ComponentActivity() {
    private val cart = Cart()
    private val products = Product.fakeData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeInstrumentedTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MenuScreen(
                        products = products,
                        cart = cart
                    )
                }
            }
        }
    }
}