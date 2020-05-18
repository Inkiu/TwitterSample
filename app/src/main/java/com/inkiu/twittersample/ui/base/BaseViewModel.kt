package com.inkiu.twittersample.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), CoroutineScope {

    private val errorHandler = CoroutineExceptionHandler { _, throwable -> throwable.printStackTrace() }

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext + errorHandler

    open fun onAttached() {}
}