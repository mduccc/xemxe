package com.indieteam.binh_lieu_app.viewmodels

import android.util.Log
import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.library.baseAdapters.BR
import com.indieteam.binh_lieu_app.views.DetailActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.view.*

class DetailViewModel : BaseObservable() {
    private var tuyenxe = ""
    private var biensoxe = ""
    private var giave = ""
    private var soghexe = ""
    private var thoigian = ""
    private var sodienthoai = ""
    private var ghichu = ""

    @Bindable
    fun getTuyenxe(): String {
        return tuyenxe
    }

    fun setTuyenxe(tuyenxe: String) {
        this.tuyenxe = tuyenxe
        notifyPropertyChanged(BR.tuyenxe)
    }

    @Bindable
    fun getBiensoxe(): String {
        return biensoxe
    }


    fun setBiensoxe(biensoxe: String) {
        this.biensoxe = biensoxe
        notifyPropertyChanged(BR.biensoxe)
    }

    @Bindable
    fun getGiave(): String {
        return giave
    }

    fun setGiave(giave: String) {
        this.giave = giave
        notifyPropertyChanged(BR.giave)
    }

    @Bindable
    fun getSoghexe(): String {
        return soghexe
    }

    fun setSoghexe(soghexe: String) {
        this.soghexe = soghexe
        notifyPropertyChanged(BR.soghexe)
    }

    @Bindable
    fun getThoigian(): String {
        return thoigian
    }

    fun setThoigian(thoigian: String) {
        this.thoigian = thoigian
        notifyPropertyChanged(BR.thoigian)
    }

    @Bindable
    fun getSodienthoai(): String {
        return sodienthoai
    }

    fun setSodienthoai(sodienthoai: String) {
        this.sodienthoai = sodienthoai
        notifyPropertyChanged(BR.sodienthoai)
    }

    @Bindable
    fun getGhichu(): String {
        return ghichu
    }

    fun setGhichu(ghichu: String) {
        this.ghichu = ghichu
        notifyPropertyChanged(BR.ghichu)
    }

    companion object {
        @BindingAdapter("exit")
        @JvmStatic
        fun exit(view: View, msg: String?) {
            view.setOnClickListener {
                Log.d("Kill", "Kill")
                (view.context as DetailActivity).apply {
                    killSelf()
                }
            }
        }

        @BindingAdapter("call")
        @JvmStatic
        fun call(view: View, msg: String?) {
            view.setOnClickListener {
                (view.context as DetailActivity).apply {
                    call(phone.text.toString())
                }
            }
        }
    }
}