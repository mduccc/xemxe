package com.indieteam.binh_lieu_app.buisiness.services

import com.indieteam.binh_lieu_app.buisiness.models.CoachModel
import com.indieteam.binh_lieu_app.buisiness.models.TaxiModel
import retrofit2.Call
import retrofit2.http.GET

interface Service {
    @GET("${EndPoint.api}${EndPoint.coach}")
    fun requestCoach(): Call<CoachModel>

    @GET("${EndPoint.api}${EndPoint.taxi}")
    fun requestTaxi(): Call<TaxiModel>
}