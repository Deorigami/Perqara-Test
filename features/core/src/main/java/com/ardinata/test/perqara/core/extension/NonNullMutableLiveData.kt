package com.ardinata.test.perqara.core.extension

import androidx.lifecycle.MutableLiveData

class NonNullMutableLiveData<T: Any>(
    private val initialValue: T
) : MutableLiveData<T>() {

    override fun getValue(): T = super.getValue() ?: initialValue
}