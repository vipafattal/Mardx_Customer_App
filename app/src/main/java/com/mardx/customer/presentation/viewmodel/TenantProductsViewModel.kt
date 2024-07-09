package com.mardx.customer.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mardx.customer.di.Dependencies
import com.mardx.customer.models.ProcessState
import com.mardx.customer.models.Product
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class TenantProductsViewModel : ViewModel() {
    private val repository = Dependencies.Data.tenantProductsRepository


    val products: StateFlow<ProcessState<List<Product>>> =
        repository.getProducts("")
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                ProcessState.Loading(),
            )

}