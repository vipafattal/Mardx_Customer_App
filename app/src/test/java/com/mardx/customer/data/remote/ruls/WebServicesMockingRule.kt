package com.mardx.customer.data.remote.ruls

import com.mardx.customer.data.webservices.TenantProductsService
import com.mardx.customer.di.factories.WebServicesFactory
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import java.util.concurrent.TimeUnit

class WebServicesMockingRule(val server: MockWebServer = MockWebServer()) : TestWatcher() {

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)


    val api: TenantProductsService =
        WebServicesFactory.buildRetrofitService(server.url("/").toUri().toString(), TenantProductsService::class.java, client)

    override fun finished(description: Description?) {
        super.finished(description)
        server.shutdown()
    }
}