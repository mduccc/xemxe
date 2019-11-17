package com.indieteam.binh_lieu_app.views

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.indieteam.binh_lieu_app.App
import com.indieteam.binh_lieu_app.R
import com.indieteam.binh_lieu_app.buisiness.models.CoachData
import com.indieteam.binh_lieu_app.buisiness.models.TaxiData
import com.indieteam.binh_lieu_app.buisiness.providers.CoachProvider
import com.indieteam.binh_lieu_app.buisiness.providers.DBManager
import com.indieteam.binh_lieu_app.buisiness.providers.LocalCallback
import com.indieteam.binh_lieu_app.buisiness.providers.TaxiProvider
import com.indieteam.binh_lieu_app.buisiness.services.RemoteCallback
import com.indieteam.binh_lieu_app.databinding.FragmentTabBinding
import com.indieteam.binh_lieu_app.viewmodels.ListViewModel
import kotlinx.android.synthetic.main.fragment_tab.*
import kotlinx.android.synthetic.main.fragment_tab.view.*
import javax.inject.Inject

class TabFragment : Fragment(), TabBehavior, RemoteCallback, LocalCallback, PermissionBehavior,
    CallBehavior {
    private val requestCode = 0
    private val permissions = arrayOf(Manifest.permission.CALL_PHONE)
    private var tab_type = -1

    companion object {
        private val tab_coach = 1
        private val tab_taxi = 2

        fun tab_coach(): Fragment {
            val tab = TabFragment()
            tab.tab_type = tab_coach
            return tab
        }

        fun tab_taxi(): Fragment {
            val tab = TabFragment()
            tab.tab_type = tab_taxi
            return tab
        }
    }

    init {
        App.appComponent.inject(this)
    }

    override fun call(number: String) {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$number")

        if (!checkPermisstion())
            requestPermisstion()
        else
            startActivity(intent)
    }

    override fun requestPermisstion() {
        ActivityCompat.requestPermissions(requireActivity(), permissions, requestCode)
    }

    override fun checkPermisstion(): Boolean {
        var has = false
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
        } else {
            has = true
        }
        return has
    }

    override fun localLoad() {
        when (tab_type) {
            tab_coach -> {
                val coachDatas = dbManager.getCoachs()
                if (coachDatas.isEmpty())
                    remoteLoad()
                else
                    onLocalSuccess("Done", coachDatas)
            }
            tab_taxi -> {
                val taxiDatas = dbManager.getTaxis()
                if (taxiDatas.isEmpty())
                    remoteLoad()
                else
                    onLocalSuccess("Done", taxiDatas)
            }
        }
    }

    override fun remoteLoad() {
        Log.d("Tab type", tab_type.toString())
        when (tab_type) {
            tab_coach -> coachProvider.fetchData(this)
            tab_taxi -> taxiProvider.fetchData(this)
        }
    }

    override fun onLocalSuccess(msg: String, data: ArrayList<*>) {
        listViewModel.setListData(data)
        listViewModel.setProcessBar(false)
    }

    override fun onLocalFailure(msg: String, t: Throwable?) {
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        listViewModel.setProcessBar(false)
    }

    override fun onRemoteSuccess(msg: String, data: ArrayList<*>) {
        Log.d("Service Callback", "onRemoteSuccess")
        when (tab_type) {
            tab_coach -> {
                dbManager.deleteCoachs()
                dbManager.insertCoach((data.map { it as CoachData }.toCollection(ArrayList())))
            }
            tab_taxi -> {
                dbManager.deleteTaxis()
                dbManager.insertTaxi((data.map { it as TaxiData }.toCollection(ArrayList())))
            }
        }
        onLocalSuccess(msg, data)
    }

    override fun onRemoteFailure(msg: String, t: Throwable?) {
        Log.d("Service Callback", "onRemoteFailure")
        Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
        listViewModel.setProcessBar(false)
    }

    @Inject
    lateinit var listViewModel: ListViewModel

    @Inject
    lateinit var taxiProvider: TaxiProvider

    @Inject
    lateinit var coachProvider: CoachProvider

    @Inject
    lateinit var dbManager: DBManager

    private val recyclerAdapter = RecyclerAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentTabBinding>(
            inflater,
            R.layout.fragment_tab,
            container,
            false
        )
        binding.listViewModel = listViewModel
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.recycler_view.adapter = recyclerAdapter
        view.recycler_view.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        Log.d("type", view.recycler_view.adapter.toString())
        localLoad()

        swipeRefreshLayout.setOnRefreshListener {
            remoteLoad()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            this.requestCode -> {
                if (grantResults.size == permissions.size && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
            }
        }
    }
}
