package com.mardx.customer.di

import com.mardx.customer.data.remote.TenantProductsRepository
import com.mardx.customer.data.webservices.common.BASE_URL
import com.mardx.customer.data.webservices.TenantProductsService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import java.util.concurrent.TimeUnit

object Dependencies {
    object WebServices {
        val tenantProductsService: TenantProductsService by lazy {
            buildRetrofitService(BASE_URL, TenantProductsService::class.java)
        }
    }

    object Data {
        val tenantProductsRepository: TenantProductsRepository by lazy {
            TenantProductsRepository(WebServices.tenantProductsService)
        }
    }

    fun <T> buildRetrofitService(
        baseUrl: String,
        clazz: Class<T>,
        clientConfigs: OkHttpClient.Builder? = null
    ): T {
        val logger =
            HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }

        val client = (clientConfigs ?: OkHttpClient.Builder())
            .addInterceptor(logger)
            .callTimeout(20000,TimeUnit.MILLISECONDS)

            .retryOnConnectionFailure(true)
            .build()

        val json = Json {
            ignoreUnknownKeys = true
        }
        return Retrofit.Builder().baseUrl(baseUrl).client(client)
            .addConverterFactory(
                json.asConverterFactory("application/json; charset=UTF8".toMediaType())
            ).build()
            .create(clazz)
    }
}