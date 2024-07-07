package com.mardx.customer.data.remote.ruls.utils

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

fun MockWebServer.enqueueFromResource(path:String, responseCode:Int) {
    val response = MockResponse().apply {
        val inputStream =
            javaClass.classLoader!!.getResourceAsStream(path)
        val responseBody = inputStream.source().buffer().readString(StandardCharsets.UTF_8)

        setBody(responseBody)
        setResponseCode(responseCode)
    }

    enqueue(response)
}