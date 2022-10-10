package com.ardinata.test.perqara.core.extension

import com.ardinata.test.perqara.core.model.Result

fun <T> toResult(data: T?): Result<T> {
    return Result(data)
}