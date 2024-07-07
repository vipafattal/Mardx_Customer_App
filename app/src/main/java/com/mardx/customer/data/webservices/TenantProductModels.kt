package com.mardx.customer.data.webservices

import com.mardx.customer.models.Product
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

object TenantProductModels {

    @Serializable
    data class ProductsData(
        @SerialName("data")
        val productDataList: ProductsDataList,
    )

    @Serializable
    data class ProductsDataList(
        val products: List<Product>,
    )


}