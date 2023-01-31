package com.cocosystems.composeinstrumented.ui.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cocosystems.composeinstrumented.R
import com.cocosystems.composeinstrumented.extensions.asMoney
import com.cocosystems.composeinstrumented.models.Product
import com.cocosystems.composeinstrumented.ui.theme.ComposeInstrumentedTheme
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


@Composable
fun ProductItem(
    product: Product,
    modifier: Modifier = Modifier,
    onAddItem: () -> Unit = {},
    onRemoveItem: () -> Unit = {}
) {
    val addAction = SwipeAction(
        icon = painterResource(id = R.drawable.ic_cart),
        background = MaterialTheme.colors.primary,
        onSwipe = { onAddItem.invoke() }
    )

    val removeAction = SwipeAction(
        icon = painterResource(id = R.drawable.ic_delete),
        background = Color.Red.copy(alpha = 0.7f),
        onSwipe = { onRemoveItem.invoke() }
    )

    SwipeableActionsBox(
        modifier = modifier.fillMaxWidth(),
        startActions = listOf(addAction),
        endActions = listOf(removeAction),
        backgroundUntilSwipeThreshold = Color.Gray
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
                .padding(20.dp, 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(Modifier.weight(1f)) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Box(Modifier.height(5.dp))
                Text(
                    text = product.description,
                    maxLines = 3, overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.caption.copy(color = Color.Gray)
                )
                Box(Modifier.height(15.dp))
                Text(
                    text = product.price.asMoney(),
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold, color = MaterialTheme.colors.primary
                    ),
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Box(Modifier.width(10.dp))

            Image(
                modifier = Modifier.size(88.dp),
                painter = painterResource(id = R.drawable.img),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductPreview() {
    ComposeInstrumentedTheme {
        ProductItem(
            Product.fakeData()[0]
        )
    }
}