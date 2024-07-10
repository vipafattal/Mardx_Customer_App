package com.mardx.customer.presentation.ui.screen

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import com.mardx.customer.presentation.ui.components.product.BuildProductsListGrid
import com.mardx.customer.presentation.ui.components.product.ProductItemTags
import com.mardx.customer.presentation.ui.components.product.ProductsListGridTags
import com.mardx.customer.presentation.ui.preview.ProductPreviewLists
import com.mardx.customer.presentation.ui.preview.TenantProductsViewModelPreview
import com.mardx.customer.presentation.viewmodel.TenantProductsViewModel
import org.junit.Rule
import org.junit.Test

class ProductsListScreenKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun should_render_buildProductListGrid_correctly() {
        val productListData = ProductPreviewLists().values.first()

        composeTestRule.setContent {
            BuildProductsListGrid(
                productListData,
            )
        }

        //Check the grid product number
        composeTestRule.onNodeWithTag(ProductsListGridTags.PRODUCTS_LIST_GRID)
            .onChildren()
            .assertCountEquals(productListData.size)

        //Check all product items are shown
        composeTestRule.onNodeWithTag(ProductItemTags.createRootTag(productListData[0].id)).assertIsDisplayed()
        composeTestRule.onNodeWithTag(ProductItemTags.createRootTag(productListData[1].id)).assertIsDisplayed()
        composeTestRule.onNodeWithTag(ProductItemTags.createRootTag(productListData[2].id)).assertIsDisplayed()
    }

    @Test
    fun buildProductListScreen_should_render_failed_state() {
        val viewModelDataPreview = TenantProductsViewModelPreview()

        composeTestRule.setContent {
            BuildProductListScreen(
                productsViewModel = TenantProductsViewModel(viewModelDataPreview.failedState)
            )
        }

        composeTestRule.onNodeWithTag(ProductsListScreenTags.PRODUCTS_ERROR).assertIsDisplayed()
    }
    @Test
    fun buildProductListScreen_should_render_loading_state() {
        val viewModelDataPreview = TenantProductsViewModelPreview()

        composeTestRule.setContent {
            BuildProductListScreen(
                productsViewModel = TenantProductsViewModel(viewModelDataPreview.loadingState)
            )
        }

        composeTestRule.onNodeWithTag(ProductsListScreenTags.PRODUCTS_LOADING).assertIsDisplayed()
    }

    @Test
    fun buildProductListScreen_should_render_success_state() {
        val viewModelDataPreview = TenantProductsViewModelPreview()

        composeTestRule.setContent {
            BuildProductListScreen(
                productsViewModel = TenantProductsViewModel(viewModelDataPreview.successState)
            )
        }

        composeTestRule.onNodeWithTag(ProductsListGridTags.PRODUCTS_LIST_GRID).assertIsDisplayed()
        composeTestRule.onNodeWithTag(ProductsListGridTags.PRODUCTS_LIST_GRID).onChildren().assertCountEquals(viewModelDataPreview.successState.data!!.size)
    }
}