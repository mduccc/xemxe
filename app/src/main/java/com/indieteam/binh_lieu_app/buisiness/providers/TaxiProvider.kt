package com.indieteam.binh_lieu_app.buisiness.providers

import com.indieteam.binh_lieu_app.buisiness.models.TaxiModel
import com.indieteam.binh_lieu_app.buisiness.services.RetrofitBuilder
import com.indieteam.binh_lieu_app.buisiness.services.RemoteCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaxiProvider {
    private lateinit var request: Call<TaxiModel>

    fun fetchData(remoteCallback: RemoteCallback) {
        request = RetrofitBuilder.instance().requestTaxi()

        request.enqueue(object : Callback<TaxiModel> {
            override fun onFailure(call: Call<TaxiModel>, t: Throwable) {
                remoteCallback.onRemoteFailure("Failure", t)
            }

            override fun onResponse(call: Call<TaxiModel>, response: Response<TaxiModel>) {
                response.body()?.let {
                    if (!request.isCanceled)
                        if (it.status)
                            remoteCallback.onRemoteSuccess("Done", it.data)
                        else
                            remoteCallback.onRemoteFailure("Request is canceled", null)
                }
            }
        })
    }
}