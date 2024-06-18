package com.man.filmku.domain.resource

sealed class Resource<T> (val data : T? = null, val message : String? = null) {

    class Success<T>(data: T?, message: String?= null) : Resource<T>(data, message)

    class Error<T>(data: T? = null, message: String?= null) : Resource<T>(data, message)

}