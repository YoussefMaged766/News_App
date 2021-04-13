package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.api.ArticalResponse
import com.example.newsapp.api.ArticlesItem
import com.example.newsapp.api.apimanager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var adapter: adapter
    lateinit var layoutManager: RecyclerView.LayoutManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyyclerView()
        getdatafromapi()
        tabclick()

    }

    fun getdatafromapi() {
        apimanager.getwebservices().getsourses("us", "391b25e55d8244bab6441f2b2426833a")
            .enqueue(object : Callback<SoursesResponse> {
                override fun onResponse(
                    call: Call<SoursesResponse>,
                    response: Response<SoursesResponse>,
                ) {
                    if (response.isSuccessful && response.body()?.status.equals("ok")) {
                        createtabs(response.body()?.sources)

                    } else {
                        Toast.makeText(this@MainActivity, response.message(), Toast.LENGTH_LONG)
                            .show()
                    }
                }

                override fun onFailure(call: Call<SoursesResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_LONG).show()
                }

            })

    }

    fun createtabs(sourses: List<SourcesItem?>?) {
        sourses?.forEach { it ->
            var tab = tablayout.newTab()
            tab.setText(it?.name)
            tab.tag = it
            tablayout.addTab(tab)

        }


    }

    fun tabclick() {
        tablayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                var item = tab?.tag as SourcesItem
                item.id?.let { getartical(it) }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                var item = tab?.tag as SourcesItem
                item.id?.let { getartical(it) }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    fun recyyclerView() {
        adapter = adapter()
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        recycler.adapter = adapter
        recycler.layoutManager=layoutManager

    }
    fun getartical(id:String){
        apimanager.getwebservices().getarticals("apple" ,"391b25e55d8244bab6441f2b2426833a","en",id)
            .enqueue(object :Callback<ArticalResponse>{
                override fun onResponse(
                    call: Call<ArticalResponse>,
                    response: Response<ArticalResponse>,
                ) {
                    if (response.isSuccessful){
                        adapter.getdata(response.body()?.articles as List<ArticlesItem>)

                    }else{
                        Toast.makeText(this@MainActivity , response.message() , Toast.LENGTH_LONG).show()
                    }

                }

                override fun onFailure(call: Call<ArticalResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity , t.localizedMessage , Toast.LENGTH_LONG).show()
                }

            })

    }

}