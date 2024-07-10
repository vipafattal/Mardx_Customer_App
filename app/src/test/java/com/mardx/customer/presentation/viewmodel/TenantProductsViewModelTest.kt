package com.mardx.customer.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mardx.customer.data.fake.FakeTenantProductRepository
import com.mardx.customer.models.ProcessState
import com.mardx.customer.models.Product
import com.mardx.customer.presentation.ui.preview.ProductPreviewItem
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TenantProductsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var sut: TenantProductsViewModel
    private lateinit var fakeRepository: FakeTenantProductRepository

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())

        fakeRepository = FakeTenantProductRepository()
        sut = TenantProductsViewModel(repository = fakeRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun testLoadingState() = runTest {
        // Initially, products state should be Loading
        assertTrue( sut.productsState.value is ProcessState.Loading)
    }

    @Test
    fun testSuccessState() = runTest {
        val sampleProducts = ProductPreviewItem().values.toList()

        fakeRepository.emitProducts(sampleProducts)

        // Verify the StateFlow has updated with the success state
        val results = sut.productsState.take(2).last()
        assertTrue(results is ProcessState.Success)
        // Verify data is same as the sampleProducts
        assertEquals(sampleProducts, (results as ProcessState.Success).data)
    }

    @Test
    fun testErrorState() = runTest {
        val errorMessage = "Network error"

        fakeRepository.emitError(errorMessage)
        val results = sut.productsState.take(2).last()

        // Verify the StateFlow has updated with the error state
        assertTrue(results is ProcessState.Failed)
        assertEquals(errorMessage, (sut.productsState.value as ProcessState.Failed).friendlyMsg)
    }
}