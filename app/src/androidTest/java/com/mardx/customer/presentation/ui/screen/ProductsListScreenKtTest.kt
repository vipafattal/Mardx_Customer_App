package com.mardx.customer.presentation.ui.screen

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import com.mardx.customer.presentation.ui.components.product.BuildProductsListGrid
import com.mardx.customer.presentation.ui.preview.ProductPreviewLists
import org.junit.Rule
import org.junit.Test

class ProductsListScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun buildProductListScreen() {
        val productListData = ProductPreviewLists().values.first()

        composeTestRule.setContent {
            BuildProductsListGrid(
                productListData,
            )
        }

        //Check the grid product number
        composeTestRule.onNodeWithTag("product_list_grid")
            .onChildren()
            .assertCountEquals(productListData.size)

        //Check all product items are shown
        composeTestRule.onNodeWithTag("item_${productListData[0].id}").assertIsDisplayed();
        composeTestRule.onNodeWithTag("item_${productListData[1].id}").assertIsDisplayed();
        composeTestRule.onNodeWithTag("item_${productListData[2].id}").assertIsDisplayed();
    }
}