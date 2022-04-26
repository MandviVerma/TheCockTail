package com.example.thecocktail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

     private var mainAdapter: MainAdapter?=null
    private var mainViewModel: MainViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        observeLiveData()
        mainViewModel!!.getData()
    }
    private fun observeLiveData() {
        mainViewModel?.cocktailDetailResponse?.observe(this, { mainResponse ->
            mainResponse?.let {
                Log.i("it", it.toString())
                mainAdapter = MainAdapter(this, it.drinks)
                val rvMain = findViewById<RecyclerView>(R.id.rvMain)
                rvMain.apply {
                    adapter = mainAdapter
                    layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
                }

            }
        })
//        mainAdapter?.notifyDataSetChanged()

    }


}