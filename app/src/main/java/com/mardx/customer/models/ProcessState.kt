package com.mardx.customer.models

import com.mardx.customer.models.ProcessState.Failed
import com.mardx.customer.models.ProcessState.Loading
import com.mardx.customer.models.ProcessState.Success

/**
 * A wrapper class for processes that can [Loading], [Success], or [Failed].
 * This class used by the library to simplify requesting data code structure (rather then using try-catch pattern)
 * which really improves the code readability. e.g. requesting data from a remote API service may fail, success, or even waiting for response.
 *
 * The type [T] represents the data type of the current process.
 */
sealed class ProcessState<T> {

    /**
     * [Loading] mean's the process loading data. e.g. waiting for remote API response.
     */
    class Loading<T> : ProcessState<T>()

    /**
     * When the process [Success] it should hold [data]. e.g. remote API response data.
     */
    data class Success<T>(val data: T?, val error: Exception? = null) : ProcessState<T>() {

    }

    /**
     * When the process [Failed] for a [error] you can use [friendlyMsg] to show the user a msg why it's failed.
     */
    data class Failed<T>(val error: String? = null, val friendlyMsg: String) : ProcessState<T>()

    /**
     * Used by library to transform the current process [T] type to another.
     * @see transform
     */
    fun <T> transformProcessType(): ProcessState<T> {
        return when (this) {
            is Loading -> Loading()
            is Success -> Success(null)
            is Failed -> Failed(error, friendlyMsg)
        }
    }

}