package com.mardx.customer.data.remote


import com.mardx.customer.models.ProcessState
import com.mardx.customer.models.Product
import kotlinx.coroutines.flow.Flow

class TenantProductsRepositoryEmpty() : TenantProductsRepository {

    override fun getProducts(tenantId: String): Flow<ProcessState<List<Product>>> {
        throw UnsupportedOperationException()
    }
}
