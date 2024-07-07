package com.mardx.customer.data.webservices

import com.mardx.customer.data.Endpoints
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TenantProductsService {

    @GET(Endpoints.products)
    suspend fun getProductsByTenantId(
        @Query("tenant_id")
        id: String
    ): Response<TenantProductModels.ProductsData>

}