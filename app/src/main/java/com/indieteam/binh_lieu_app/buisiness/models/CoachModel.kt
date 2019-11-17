package com.indieteam.binh_lieu_app.buisiness.models

data class CoachData(val id: String, val tuyenxe: String, val biensoxe: String, val giave: String, val soghexe: String, val thoigian1: String, val thoigian2: String, val sodienthoai: String, val ghichu: String)

data class CoachModel (val status: Boolean, val msg: String, val data: ArrayList<CoachData>)