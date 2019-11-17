package com.indieteam.binh_lieu_app.buisiness.models

data class TaxiData (val id: String, val tentaxi: String, val sodienthoai: String)

data class TaxiModel (val status: Boolean, val msg: String, val data: ArrayList<TaxiData>)