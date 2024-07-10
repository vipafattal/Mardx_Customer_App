package com.mardx.customer.presentation.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mardx.customer.models.ProcessState
import com.mardx.customer.models.Product

class TenantProductsViewModelPreview : PreviewParameterProvider<ProcessState<List<Product>>>{

    val loadingState = ProcessState.Loading<List<Product>>()
    val failedState = ProcessState.Failed<List<Product>>(friendlyMsg = "Failed loading tenant products")
    val successState = ProcessState.Success(data = ProductPreviewItem().values.toList())

    override val values: Sequence<ProcessState<List<Product>>> = sequenceOf(
        loadingState,
        failedState,
        successState,
    )
}