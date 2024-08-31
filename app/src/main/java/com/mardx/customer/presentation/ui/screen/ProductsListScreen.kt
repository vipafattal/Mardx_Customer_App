package com.mardx.customer.presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewFontScale
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mardx.customer.data.remote.TenantProductsRepositoryEmpty
import com.mardx.customer.models.ProcessState
import com.mardx.customer.models.Product
import com.mardx.customer.presentation.ui.components.product.BuildProductsListGrid
import com.mardx.customer.presentation.ui.preview.TenantProductsViewModelPreview
import com.mardx.customer.presentation.ui.theme.MardxCustomerAppTheme
import com.mardx.customer.presentation.viewmodel.TenantProductsViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun BuildProductListScreen(
    productsViewModel: TenantProductsViewModel = koinViewModel(),
    innerPadding: PaddingValues = PaddingValues(0.dp)
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding), contentAlignment = Alignment.Center
    ) {
        val productsState by productsViewModel.productsState.collectAsStateWithLifecycle()
        when (val currentState = productsState) {
            is ProcessState.Loading -> CircularProgressIndicator(
                Modifier.testTag(
                    ProductsListScreenTags.PRODUCTS_LOADING
                )
            )

            is ProcessState.Failed -> Text(
                modifier = Modifier.testTag(ProductsListScreenTags.PRODUCTS_ERROR),
                text = currentState.friendlyMsg
            )

            is ProcessState.Success -> BuildProductsListGrid(currentState.data!!)
        }
    }
}

object ProductsListScreenTags {
    private const val TAG = "products_list_screen"

    const val PRODUCTS_LOADING = "${TAG}_loading"
    const val PRODUCTS_ERROR = "${TAG}_error"
}

@PreviewFontScale
//@PreviewScreenSizes
//@PreviewLightDark
//@PreviewDynamicColors
@Preview(showBackground = true)
@PreviewDynamicColors
@Composable
private fun ProductListPreview(@PreviewParameter(TenantProductsViewModelPreview::class) productListState: ProcessState<List<Product>>) {
    MardxCustomerAppTheme {
        BuildProductListScreen(
            productsViewModel = TenantProductsViewModel(
                productsDefaultState = productListState,
                repository = TenantProductsRepositoryEmpty(),
            )
        )
    }
}

