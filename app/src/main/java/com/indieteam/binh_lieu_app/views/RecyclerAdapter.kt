package com.indieteam.binh_lieu_app.views

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.indieteam.binh_lieu_app.App
import com.indieteam.binh_lieu_app.R
import com.indieteam.binh_lieu_app.buisiness.models.CoachData
import com.indieteam.binh_lieu_app.buisiness.models.TaxiData
import kotlinx.android.synthetic.main.item.view.*
import javax.inject.Inject

class RecyclerAdapter(private val callBehavior: CallBehavior, private val navigator: Navigator) :
    RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {


    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var context: Context

    private val listData = ArrayList<Any>()

    fun setData(listData: ArrayList<*>) {
        clearData()
        this.listData.addAll(listData)
        notifyDataSetChanged()
    }

    private fun clearData() {
        listData.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val view = holder.itemView
        view.apply {
            if (listData[position] is CoachData) {
                val ele = listData[position] as CoachData
                icon.setImageResource(R.drawable.ic_bus)
                name.text = "${ele.tuyenxe}"
                other_info.text = "${ele.biensoxe} - ${ele.soghexe} chỗ - ${ele.giave}" + " ${Label.vnd}"
                time.text = "${Label.thoigian} ${ele.thoigian1} - ${ele.thoigian2}"
                phone.text = "${Label.phone} ${ele.sodienthoai}"

                call.setOnClickListener {
                    Log.d("Call to", ele.sodienthoai)
                    callBehavior.call(ele.sodienthoai)
                }

                setOnClickListener {
                    navigator.goToActivity("id", ele.id, DetailActivity::class.java)
                }
            }
            if (listData[position] is TaxiData) {
                val ele = listData[position] as TaxiData
                icon.setImageResource(R.drawable.ic_taxi)
                name.text = ele.tentaxi
                other_info.visibility = GONE
                time.visibility = GONE
                phone.text = ele.sodienthoai

                call.setOnClickListener {
                    Log.d("Call to", ele.sodienthoai)
                    callBehavior.call(ele.sodienthoai)
                }
            }
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}