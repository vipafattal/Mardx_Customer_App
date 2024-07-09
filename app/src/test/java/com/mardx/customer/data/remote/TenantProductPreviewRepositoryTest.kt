package com.mardx.customer.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mardx.customer.data.remote.ruls.WebServicesMockingRule
import com.mardx.customer.data.remote.ruls.utils.enqueueFromResource
import com.mardx.customer.models.ProcessState
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class TenantProductPreviewRepositoryTest {


    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var apiMockingRule = WebServicesMockingRule()

    //Subject under test.
    private lateinit var sut: TenantProductsRepository

    @Before
    fun setup() {
        sut = TenantProductsRepository(apiMockingRule.api)
    }


    @Test
    fun `getProducts Should products correctly when it's 200`() = runTest {

        apiMockingRule.server.enqueueFromResource("api-response/get_tenant_product_successful_response.json",200)

        val productsProcess = sut.getProducts("").take(2).toList()

        Assert.assertTrue(productsProcess.first() is ProcessState.Loading)
        Assert.assertTrue(productsProcess.last() is ProcessState.Success)

        val products = (productsProcess.last() as ProcessState.Success).data!!
        Assert.assertTrue(products.size == 118)
    }
}