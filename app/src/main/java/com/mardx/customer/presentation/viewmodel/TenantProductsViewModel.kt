package com.mardx.customer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mardx.customer.data.remote.TenantProductsRepository
import com.mardx.customer.models.ProcessState
import com.mardx.customer.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class TenantProductsViewModel(
    private val productsDefaultState: ProcessState<List<Product>>? = null,
    private val repository: TenantProductsRepository,
) : ViewModel() {


    var productsState: StateFlow<ProcessState<List<Product>>> = createProductsState()

    private fun createProductsState(): StateFlow<ProcessState<List<Product>>> {
        return if (productsDefaultState != null) MutableStateFlow(value = productsDefaultState)
        else repository.getProducts("9fa1c91a-4c3d-4668-9e14-b80398ed3517")
            .stateIn(
                viewModelScope,
                SharingStarted.Lazily,
                ProcessState.Loading(),
            )
    }

}