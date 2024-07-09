package com.mardx.customer.presentation.ui.components.product

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.mardx.customer.models.Product
import com.mardx.customer.presentation.ui.preview.ProductPreviewLists
import com.mardx.customer.presentation.ui.theme.MardxCustomerAppTheme

@Composable
fun BuildProductsListGrid(
    productList: List<Product>
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = Modifier
            .testTag("product_list_grid")
            .padding(horizontal = 8.dp)
    ) {
        items(productList) { product ->
            ProductItem(product = product)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductListPreview(@PreviewParameter(ProductPreviewLists::class) productList: List<Product>) {
    MardxCustomerAppTheme {
        BuildProductsListGrid(
            productList
        )
    }
}