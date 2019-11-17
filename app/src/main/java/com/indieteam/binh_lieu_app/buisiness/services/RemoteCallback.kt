package com.indieteam.binh_lieu_app.buisiness.services

interface RemoteCallback {
    fun onRemoteSuccess(msg: String, data: ArrayList<*>)
    fun onRemoteFailure(msg: String, t: Throwable?)
}