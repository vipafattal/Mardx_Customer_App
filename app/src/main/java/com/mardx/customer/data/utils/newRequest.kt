package com.mardx.customer.data.utils

import com.mardx.customer.models.ProcessState
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import java.io.IOException
import java.net.ProtocolException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by ${User} on ${Date}
 */

/** @suppress */
internal inline fun <reified T> newRequest(
    crossinline requestBlock: suspend () -> Response<T>
) = flow<ProcessState<T>> {
    onRequestCompleted(
        request = {
            emit(ProcessState.Loading())
            requestBlock()
        },
        onSuccess = { emit(ProcessState.Success(it)) },
        onError = { reason, userMsg -> emit(ProcessState.Failed(reason, userMsg)) }
    )
}

@PublishedApi
internal inline fun <reified T> onRequestCompleted(
    request: () -> Response<T>,
    onSuccess: (data: T) -> Unit,
    onError: (reason: String?, friendlyMsg: String) -> Unit
) {

    var userErrorMsg: String = "unknown_error"
    var errorReason: String? = "Unknown error"
    var data: T? = null
    val className = T::class.java.name
    var errorCode = -1

    try {

        val response = request()

        if (response.isSuccessful)
            data = response.body()
        else {
            printLog("Unknown error for $className", response.errorBody().toString())
            errorCode = response.code()
        }

    } catch (e: UnknownHostException) {
        printLog("Get response for $className", e.message ?: "Unknown error")
        userErrorMsg = "no_internet"
    } catch (io: SocketTimeoutException) {
        printLog("Get response for $className", io.message ?: "Unknown error")
        errorReason = io.message
        userErrorMsg = "timout"
    } catch (so: SocketException) {
        printLog("Get response for $className", so.message ?: "Unknown error")
        errorReason = so.message
        userErrorMsg = "connection_reset"
    } catch (pro: ProtocolException) {
        printLog("Get response for $className", pro.message ?: "Unknown error")
        errorReason = pro.message

        userErrorMsg = "connection_error"
    } catch (io: IOException) {
        printLog("Get response for $className", io.message ?: "Unknown error")
        userErrorMsg = "service_not_available"
        errorReason = io.message
    } catch (e: Exception) {
        printLog("Get response for $className", e.message ?: "Unknown error")
        userErrorMsg ="unknown_error"
        errorReason = e.message
    }

    if (data != null) onSuccess(data)
    else onError("reason:$errorReason, code:$errorCode", userErrorMsg)
}

fun printLog(tag: String, msg: String) {
    print("$tag, $msg")
}