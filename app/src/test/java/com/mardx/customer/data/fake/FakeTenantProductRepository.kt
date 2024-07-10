package com.mardx.customer.data.fake

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mardx.customer.data.remote.TenantProductsRepository
import com.mardx.customer.models.ProcessState
import com.mardx.customer.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule

class FakeTenantProductRepository :
    TenantProductsRepository {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val fakeDataFlow: MutableStateFlow<ProcessState<List<Product>>> =
        MutableStateFlow(ProcessState.Loading())

    override fun getProducts(tenantId: String): Flow<ProcessState<List<Product>>> {
        return fakeDataFlow
    }

    suspend fun emitError(errorMessage: String) {
        fakeDataFlow.emit(ProcessState.Failed(friendlyMsg = errorMessage))
    }

    suspend fun emitProducts(sampleProducts: List<Product>) {
        fakeDataFlow.emit(ProcessState.Success(data = sampleProducts))
    }


}