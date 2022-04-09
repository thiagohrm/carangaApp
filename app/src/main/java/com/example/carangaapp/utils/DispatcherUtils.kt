package com.example.carangaapp.utils

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherUtils {
    val main : CoroutineDispatcher
    val io : CoroutineDispatcher
    val default : CoroutineDispatcher
    val unconfined : CoroutineDispatcher
}