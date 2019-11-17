package com.indieteam.binh_lieu_app.viewmodels

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.indieteam.binh_lieu_app.views.RecyclerAdapter

class ListViewModel : BaseObservable() {

    private val listData = ArrayList<Any>()

    private var processBar = true

    @Bindable
    fun getProcessBar(): Boolean{
        return processBar
    }

    fun setProcessBar(boolean: Boolean){
        processBar = boolean
        notifyPropertyChanged(BR.processBar)
    }

    @Bindable
    fun getListData(): ArrayList<*> {
        return listData
    }

    fun setListData(listData: ArrayList<*>) {
        this.listData.clear()
        this.listData.addAll(listData)
        notifyPropertyChanged(BR.listData)
    }

    companion object {
        @BindingAdapter("data")
        @JvmStatic
        fun run(recyclerView
                : RecyclerView, data: ArrayList<*>?) {
            if (data == null)
                Log.d("data", "null")

            if (data != null) {
                Log.d("type", recyclerView.adapter.toString())
                if (recyclerView.adapter is RecyclerAdapter) {
                    Log.d("recyclerView.adapter", "is instance")
                    (recyclerView.adapter as RecyclerAdapter).apply {
                        setData(data)
                    }
                } else
                    Log.d("recyclerView.adapter", "is not instance")
            } else
                Log.d("params", "null")

        }
    }
}