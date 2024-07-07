package com.mardx.customer.data.remote

import com.mardx.customer.data.webservices.TenantProductsService
import com.mardx.customer.data.utils.newRequest
import com.mardx.customer.models.ProcessState
import com.mardx.customer.models.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TenantProductsRepository(private val service: TenantProductsService) {

    suspend fun getProducts(tenantId: String): Flow<ProcessState<List<Product>>> {

        return newRequest { service.getProductsByTenantId(tenantId) }.map {
            if (it is ProcessState.Success)
                ProcessState.Success(it.data!!.productDataList.products)
            else
                it.transformProcessType()

        }
    }
}
