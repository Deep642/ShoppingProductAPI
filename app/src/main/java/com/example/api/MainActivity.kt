package com.example.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var myAdapter: MyAdapter
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.RV)
        val retrofitBuilder=Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIinterface::class.java)

        val retrofitData=retrofitBuilder.getProductData()
        retrofitData.enqueue(object : Callback<DummyData?> {
            override fun onResponse(call: Call<DummyData?>, response: Response<DummyData?>) {
                val productList=response.body()?.products!!

                myAdapter=MyAdapter(this@MainActivity,productList)
                recyclerView.adapter=myAdapter
                recyclerView.layoutManager=LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(call: Call<DummyData?>, t: Throwable) {
                    Log.d("Main Activity","onFailure: "+t.message)
            }
        })
    }
}