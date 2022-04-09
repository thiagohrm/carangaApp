package com.example.carangaapp.utils

sealed class ResourceUtils<T> (val data : T? ,val  message : String?){
    class Success <T>(data: T) : ResourceUtils<T>(data,null)
    class Error <T> (message: String) : ResourceUtils<T> (null, message)
}