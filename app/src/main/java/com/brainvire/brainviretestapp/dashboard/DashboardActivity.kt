package com.brainvire.brainviretestapp.dashboard

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.brainvire.brainviretestapp.R
import com.brainvire.brainviretestapp.pojo.MainResponsePojo
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_dashboard.*
import om.brainvire.brainviretestapp.APIConfiguration.ApiResponseHandler
import om.brainvire.brainviretestapp.APIConfiguration.RetrofitConfiguration
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardActivity : AppCompatActivity(),ApiResponseHandler<Response<Any?>> {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        //start loader
        showLoading()

        rvDataList.layoutManager = LinearLayoutManager(this)
        //call API
        loadData()

    }

    //API calling
    private fun loadData()
    {
        try {
            RetrofitConfiguration.apiManagement()!!.getDataList("2018-01-01", "2018-09-01", "USD")!!
                .enqueue(object : Callback<Any?> {
                    override fun onFailure(call: Call<Any?>?, t: Throwable?) {
                        Toast.makeText(this@DashboardActivity, "OnFailure", Toast.LENGTH_SHORT).show()
                        fail(t)
                    }
                    override fun onResponse(call: Call<Any?>?, response: Response<Any?>?) {
                        if (response!!.isSuccessful) {
                            success(response)
                        }
                    }
                })
        }
        catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun noInternet() {}

    override fun success(response: Response<Any?>?) {
        val data = Gson().fromJson(response!!.body().toString(), MainResponsePojo::class.java)
        if(data.rates!=null)
        {
            val mainList:HashMap<String,Any> = Gson().fromJson(data.rates.toString(), HashMap::class.java) as HashMap<String,Any>
            rvDataList.adapter = DashboardAdapter(this@DashboardActivity,mainList)
        }
        hideLoading()
        rvDataList.visibility = View.VISIBLE

    }
    override fun fail(error: Throwable?) {
        hideLoading()
        Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show()
        rvDataList.visibility = View.GONE
        llRetry.visibility = View.VISIBLE
    }

    override fun showLoading() {
        llProgressBar.visibility = View.VISIBLE
        if (llRetry.visibility == View.VISIBLE) llRetry.visibility = View.GONE
    }

    override fun hideLoading() {
        llProgressBar.visibility = View.GONE
    }


}