package com.example.movie.utils

sealed class Resource<T>(message:String?=null,data:T?=null){
    class Success<S>(data:S):Resource<S>(data = data)
    class Error<E>(message:String,data:E?=null):Resource<E>(data = data,message = message)
    class  Loading<L>(val isLoading:Boolean=true):Resource<L>()

}