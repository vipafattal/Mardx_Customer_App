package com.mardx.customer.presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mardx.customer.models.ProcessState
import com.mardx.customer.models.Product
import com.mardx.customer.presentation.ui.components.product.BuildProductsListGrid
import com.mardx.customer.presentation.ui.preview.ProductPreviewLists
import com.mardx.customer.presentation.ui.theme.MardxCustomerAppTheme
import com.mardx.customer.presentation.viewmodel.TenantProductsViewModel

@Composable
fun BuildProductListScreen(productsViewModel: TenantProductsViewModel = viewModel(), innerPadding: PaddingValues) {
    Box(modifier = Modifier.padding(innerPadding)) {
        val productsState by productsViewModel.products.collectAsStateWithLifecycle()
        when(val currentState = productsState){
            is ProcessState.Loading -> CircularProgressIndicator()
            is ProcessState.Failed -> Text(text=currentState.friendlyMsg)
            is ProcessState.Success -> BuildProductsListGrid(currentState.data!!)
        }
    }
}
@Preview(showBackground = true)
@Composable
private fun ProductListPreview(@PreviewParameter(ProductPreviewLists::class) productList: List<Product>) {
    MardxCustomerAppTheme {
        BuildProductListScreen(
            innerPadding = PaddingValues(0.dp)
        )
    }
}

