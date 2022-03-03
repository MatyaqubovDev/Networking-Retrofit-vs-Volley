package dev.matyaqubov.networking.Volley

interface VolleyHandler {

    fun onSuccess(response:String?)
    fun onError(error:String?)

}