package com.ardinata.test.perqara.core.model

import com.google.gson.annotations.SerializedName

class ResultDto<T>(
    @SerializedName("results")
    val data: T? = null,
) {
    companion object {
        const val SUCCESS = "000"
        fun <T> ResultDto<*>.toResult(data : T?) = Result(
            data = data
        )
    }
}