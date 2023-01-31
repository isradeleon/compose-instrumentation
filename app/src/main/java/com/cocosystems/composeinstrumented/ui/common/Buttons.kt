package com.cocosystems.composeinstrumented.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cocosystems.composeinstrumented.ui.theme.ComposeInstrumentedTheme

@Composable
fun FullWidthButton(
    caption: String,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colors.onPrimary,
    backgroundColor: Color = MaterialTheme.colors.primary,
    onClick: () -> Unit = {}
) {
    Box(
        modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .clickable { onClick.invoke() }
    ) {
        Box(
            Modifier
                .fillMaxWidth()
                .padding(13.dp)
        ) {
            Text(
                text = caption,
                style = MaterialTheme.typography.body1.copy(
                    color = textColor,
                    fontWeight = FontWeight.Bold
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMainButton() {
    ComposeInstrumentedTheme {
        Column(Modifier.fillMaxSize()) {
            FullWidthButton("Order")
        }
    }
}