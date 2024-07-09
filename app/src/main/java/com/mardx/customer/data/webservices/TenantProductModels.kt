package com.mardx.customer.data.webservices

import com.mardx.customer.models.Product
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

object TenantProductModels {

    @Serializable
    data class ProductsData(
        val products: List<Product>,
    )


}