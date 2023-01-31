package com.cocosystems.composeinstrumented.ui.product

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.cocosystems.composeinstrumented.extensions.asMoney
import com.cocosystems.composeinstrumented.models.Cart
import com.cocosystems.composeinstrumented.models.Product
import com.cocosystems.composeinstrumented.ui.common.FullWidthButton
import com.cocosystems.composeinstrumented.ui.tests.TestTags
import com.cocosystems.composeinstrumented.ui.theme.ComposeInstrumentedTheme

@Composable
fun MenuScreen(
    products: List<Product>,
    cart: Cart
) {
    val total = cart.total.collectAsState()

    // Item not in cart dialog
    val showDialog = remember { mutableStateOf(false) }
    if (showDialog.value)
        NotInCartErrorDialog(showDialog = showDialog)

    Column(Modifier.fillMaxSize()) {

        Column(
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            products.forEach { item ->
                key(item.id) {
                    ProductItem(
                        product = item,
                        modifier = Modifier.testTag(item.id),
                        onAddItem = {
                            cart.addItem(item)
                        },
                        onRemoveItem = {
                            val wasRemoved = cart.removeItem(item)
                            if (!wasRemoved)
                                showDialog.value = true
                        }
                    )

                    Divider()
                }
            }
        }

        if (total.value > 0)
            FullWidthButton(
                caption = "Carrito "+total.value.asMoney(),
                modifier = Modifier.testTag(TestTags.CART_TOTAL_BUTTON)
            )
    }
}

@Composable
fun NotInCartErrorDialog(
    showDialog: MutableState<Boolean>
) {
    AlertDialog(
        onDismissRequest = { showDialog.value = false },
        confirmButton = {
            TextButton(onClick = {
                showDialog.value = false
            }) {
                Text("Cerrar")
            }
        },
        title = {
            Text("Error")
        },
        text = {
            Text("Este item no esta en el carrito")
        },
        modifier = Modifier // Set the width and padding
            .fillMaxWidth()
            .padding(21.dp)
            .testTag(TestTags.CART_ERROR_DIALOG),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.White,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMenuScreen() {
    ComposeInstrumentedTheme {
        MenuScreen(
            Product.fakeData(),
            Cart()
        )
    }
}