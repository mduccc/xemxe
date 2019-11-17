package com.indieteam.binh_lieu_app.views

interface Navigator {
    fun goToActivity(key: String, value: String, target: Class<*>)
    fun killSelf()
}