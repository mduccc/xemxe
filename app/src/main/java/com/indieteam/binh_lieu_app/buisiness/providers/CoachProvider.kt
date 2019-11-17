package com.indieteam.binh_lieu_app.buisiness.providers

import com.indieteam.binh_lieu_app.buisiness.models.CoachModel
import com.indieteam.binh_lieu_app.buisiness.services.RetrofitBuilder
import com.indieteam.binh_lieu_app.buisiness.services.RemoteCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoachProvider {
    private lateinit var request: Call<CoachModel>

    fun fetchData(remoteCallback: RemoteCallback) {
        request = RetrofitBuilder.instance().requestCoach()

        request.enqueue(object : Callback<CoachModel> {
            override fun onFailure(call: Call<CoachModel>, t: Throwable) {
                remoteCallback.onRemoteFailure("Failure", t)
            }

            override fun onResponse(call: Call<CoachModel>, response: Response<CoachModel>) {
                response.body()?.let {
                    if (!request.isCanceled)
                        if (it.status)
                            remoteCallback.onRemoteSuccess(
                                "Done",
                                it.data
                            )
                        else
                            remoteCallback.onRemoteFailure("Request is canceled", null)
                }
            }

        })
    }
}