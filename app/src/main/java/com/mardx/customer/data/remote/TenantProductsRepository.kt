package com.mardx.customer.data.remote

import com.mardx.customer.models.ProcessState
import com.mardx.customer.models.Product
import kotlinx.coroutines.flow.Flow

interface TenantProductsRepository {

    fun getProducts(tenantId: String): Flow<ProcessState<List<Product>>>

}
