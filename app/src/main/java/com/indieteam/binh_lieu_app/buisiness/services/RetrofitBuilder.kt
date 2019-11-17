package com.indieteam.binh_lieu_app.buisiness.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    companion object {
        private var retrofit: Service? = null

        private fun build(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(EndPoint.domain)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun instance(): Service {
            retrofit = if (retrofit == null)
                build().create(
                    Service::class.java)
            else
                retrofit

            return retrofit!!
        }
    }
}