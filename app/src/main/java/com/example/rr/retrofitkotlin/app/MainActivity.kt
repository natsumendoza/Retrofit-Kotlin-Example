package com.example.rr.retrofitkotlin.app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.rr.retrofitkotlin.app.data.model.repo.Repo
import com.example.rr.retrofitkotlin.app.service.GitHubService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), Callback<List<Repo>> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(GitHubService::class.java)
        val call = service.listRepos("natsumendoza")
        call.enqueue(this)

    }

    override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
        if (response.isSuccessful) {
            val repos = response.body()
            repos?.forEach {
                println(it.name)
            }
        } else {
            println(response.errorBody())
        }
    }

    override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
