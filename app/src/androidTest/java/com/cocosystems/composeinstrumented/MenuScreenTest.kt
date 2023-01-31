package com.cocosystems.composeinstrumented

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cocosystems.composeinstrumented.models.Cart
import com.cocosystems.composeinstrumented.models.Product
import com.cocosystems.composeinstrumented.ui.product.MenuScreen
import com.cocosystems.composeinstrumented.ui.tests.TestTags
import com.cocosystems.composeinstrumented.ui.theme.ComposeInstrumentedTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MenuScreenTest {
    private val products = Product.fakeData()
    private val cart = Cart()

    @get:Rule
    val composeRule = createComposeRule()

    private fun composeContentSetup(){
        composeRule.setContent {
            ComposeInstrumentedTheme {
                MenuScreen(
                    products = products,
                    cart = cart
                )
            }
        }
    }

    @Test
    fun validateTotalButton_InitialState() {
        composeContentSetup()

        // Assert total button doesn't exist
        composeRule.onNodeWithTag(
            TestTags.CART_TOTAL_BUTTON
        ).assertDoesNotExist()
    }

    @Test
    fun validateTotalButton_Behavior() {
        composeContentSetup()

        // Call swipe on every product item
        for (product in products)
            composeRule.onNodeWithTag(product.id)
                .performTouchInput {
                    swipeRight()
                }

        // Check total is visible now
        composeRule.onNodeWithTag(
            TestTags.CART_TOTAL_BUTTON
        ).assertExists().assertIsDisplayed()

        // Call swipe on every product item
        for (product in products)
            composeRule.onNodeWithTag(product.id)
                .performTouchInput {
                    swipeLeft()
                }

        // Assert total button is gone again
        composeRule.onNodeWithTag(
            TestTags.CART_TOTAL_BUTTON
        ).assertDoesNotExist()
    }

}