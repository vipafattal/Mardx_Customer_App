package com.mardx.customer.framework

import com.mardx.customer.data.BASE_URL
import com.mardx.customer.data.webservices.TenantProductsService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

object Dependencies {
    object WebServices {
        val tenantProductsService: TenantProductsService by lazy {
            buildRetrofitService(BASE_URL, TenantProductsService::class.java)
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