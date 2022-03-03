package dev.matyaqubov.networking

interface VolleyHandler {

    fun onSuccess(response:String?)
    fun onError(error:String?)

}