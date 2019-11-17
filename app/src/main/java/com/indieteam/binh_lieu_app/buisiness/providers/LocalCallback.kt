package com.indieteam.binh_lieu_app.buisiness.providers

interface LocalCallback {
    fun onLocalSuccess(msg: String, data: ArrayList<*>)
    fun onLocalFailure(msg: String, t: Throwable?)
}