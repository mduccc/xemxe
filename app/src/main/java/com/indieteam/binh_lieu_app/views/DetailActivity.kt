package com.indieteam.binh_lieu_app.views

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.indieteam.binh_lieu_app.App
import com.indieteam.binh_lieu_app.R
import com.indieteam.binh_lieu_app.buisiness.providers.DBManager
import com.indieteam.binh_lieu_app.databinding.ActivityDetailBinding
import com.indieteam.binh_lieu_app.viewmodels.DetailViewModel
import javax.inject.Inject

class DetailActivity : AppCompatActivity(), CallBehavior, Navigator, PermissionBehavior {
    @Inject
    lateinit var detailViewModel: DetailViewModel
    @Inject
    lateinit var dbManager: DBManager

    override fun call(number: String) {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$number")

        if (!checkPermisstion())
            requestPermisstion()
        else
            startActivity(intent)
    }

    override fun goToActivity(key: String, value: String, target: Class<*>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun killSelf() {
        finish()
    }

    override fun requestPermisstion() {
        ActivityCompat.requestPermissions(
            this,
            AppDefine.permissions,
            AppDefine.requestCode
        )
    }

    override fun checkPermisstion(): Boolean {
        var has = false
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
        } else {
            has = true
        }
        return has
    }

    init {
        App.appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityDetailBinding>(this, R.layout.activity_detail)
        binding.detailViewModel = detailViewModel
        binding.executePendingBindings()

        val id = intent.getStringExtra("id")
        id?.let { _id ->
            val coachData = dbManager.getCoach(_id)
            coachData?.let { _coach ->
                detailViewModel.apply {
                    setTuyenxe(_coach.tuyenxe)
                    setGiave(Label.giave + _coach.giave + " " + Label.vnd)
                    setThoigian(Label.thoigian + _coach.thoigian1 + " - " + _coach.thoigian2)
                    setSodienthoai(Label.phone + _coach.sodienthoai)
                    setSoghexe(Label.soghe + _coach.soghexe)
                    setBiensoxe(Label.bienso + _coach.biensoxe)
                    setGhichu(Label.ghichu + _coach.ghichu)
                }
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            AppDefine.requestCode -> {
                if (grantResults.size == permissions.size && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
            }
        }
    }
}
