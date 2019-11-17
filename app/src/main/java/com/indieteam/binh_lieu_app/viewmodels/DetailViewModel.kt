package com.indieteam.binh_lieu_app.viewmodels

import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter

class DetailViewModel {
    private var tuyenxe = ""
    private var biensoxe = ""
    private var giave = ""
    private var soghexe = ""
    private var thoigian1 = ""
    private var thoigian2 = ""
    private var sodienthoai = ""
    private var ghichu = ""

    @Bindable
    fun getTuyenxe(): String {
        return tuyenxe
    }

    fun setTuyenxe(tuyenxe: String) {
        this.tuyenxe = tuyenxe
    }

    @Bindable
    fun getBiensoxe(): String {
        return biensoxe
    }


    fun setBiensoxe(biensoxe: String) {
        this.biensoxe = biensoxe
    }

    @Bindable
    fun getGiave(): String {
        return giave
    }

    fun setGiave(giave: String) {
        this.giave = giave
    }

    @Bindable
    fun getSoghexe(): String {
        return soghexe
    }

    fun setSoghexe(soghexe: String) {
        this.soghexe = soghexe
    }

    @Bindable
    fun getThoigian1(): String {
        return thoigian1
    }

    fun setThoigian1(thoigian1: String) {
        this.thoigian1 = thoigian1
    }

    @Bindable
    fun getThoigian2(): String {
        return thoigian2
    }

    @Bindable
    fun setThoigian2(thoigian2: String) {
        this.thoigian2 = thoigian2
    }

    @Bindable
    fun getSodienthoai(): String {
        return sodienthoai
    }

    fun setSodienthoai(sodienthoai: String) {
        this.sodienthoai = sodienthoai
    }

    @Bindable
    fun getGhichu(): String {
        return ghichu
    }

    fun setGhichu(ghichu: String) {
        this.ghichu = ghichu
    }

    companion object {
        @BindingAdapter("call")
        @JvmStatic
        fun call(view: View, msg: String?) {

        }

        @BindingAdapter("exit")
        @JvmStatic
        fun exit(view: View, msg: String?) {

        }
    }

}