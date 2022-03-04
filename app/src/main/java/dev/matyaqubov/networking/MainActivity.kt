package dev.matyaqubov.networking

import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import dev.matyaqubov.networking.Retrofit.PosterResp
import dev.matyaqubov.networking.Retrofit.RetrofitHttp
import dev.matyaqubov.networking.Volley.VolleyHandler
import dev.matyaqubov.networking.Volley.VolleyHttp
import dev.matyaqubov.networking.adapter.PosterAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var progress:ProgressBar
    private lateinit var b_floating:FloatingActionButton
    private  var posters =ArrayList<Poster>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        b_floating=findViewById(R.id.b_floating)
        progress=findViewById(R.id.progress)
        progress.visibility=View.GONE
        recyclerView.layoutManager=GridLayoutManager(this,1)
        apiPosterList()

        b_floating.setOnClickListener {

        }

        val poster = Poster(1, 1, "PDP", "Online")



    }

    private fun apiPosterList() {
        progress.visibility=View.VISIBLE
        VolleyHttp.get(VolleyHttp.API_LIST_POST,VolleyHttp.paramsEmpty(),object :VolleyHandler{
            override fun onSuccess(response: String?) {
                val postArray=Gson().fromJson(response,Array<Poster>::class.java)
                posters.clear()
                posters.addAll(postArray)
                progress.visibility=View.GONE
                refreshAdapter(posters)
            }

            override fun onError(error: String?) {
                progress.visibility=View.GONE
            }

        })
    }

    private fun refreshAdapter(posters: java.util.ArrayList<Poster>) {
        recyclerView.adapter=PosterAdapter(this,posters)
    }

    fun dialogPoster(post: Poster) {
        AlertDialog.Builder(this)
            .setTitle("Delete post")
            .setMessage("Are you sure to delete this post")
            .setPositiveButton(
                android.R.string.yes
            ) { _, _ ->
                apiPosterDelete(post)
            }
            .setNegativeButton(android.R.string.no, null)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .show()
    }

private fun apiPosterDelete(poster: Poster){
    VolleyHttp.del(VolleyHttp.API_DELETE_POST + poster.id,object :VolleyHandler{
        override fun onSuccess(response: String?) {
            Logger.d("@@@@",response!!)
            apiPosterList()
        }

        override fun onError(error: String?) {

        }

    })
}














    fun workWithVolley(poster: Poster) {

//            getForVolley()
//            post(poster)
//            put(poster)
//            del(poster)
    }

    fun getForVolley() {

        VolleyHttp.get(VolleyHttp.API_LIST_POST, VolleyHttp.paramsEmpty(), object : VolleyHandler {
            override fun onSuccess(response: String?) {
                Logger.d("VolleyHttp", response!!)
                //tv_responce.text = response
            }

            override fun onError(error: String?) {
                Logger.d("VolleyHttp", error!!)
            }

        })

    }

    fun post(poster: Poster) {
        VolleyHttp.post(
            VolleyHttp.API_CREATE_POST,
            VolleyHttp.paramsCreate(poster),
            object : VolleyHandler {
                override fun onSuccess(response: String?) {
                    Logger.d("post", response!!)
                    //tv_responce.text = response
                }

                override fun onError(error: String?) {
                    Logger.d("post", error!!)
                   // tv_responce.text = error
                }

            })
    }

    fun put(poster: Poster) {
        VolleyHttp.put(
            VolleyHttp.API_UPDATE_POST + poster.id,
            VolleyHttp.paramsUpdate(poster),
            object : VolleyHandler {
                override fun onSuccess(response: String?) {
                    Logger.d("put", response!!)
                    //tv_responce.text = response
                }

                override fun onError(error: String?) {
                    Logger.d("put", error!!)
                    //tv_responce.text = error
                }

            })
    }

    fun del(poster: Poster) {

        VolleyHttp.del(VolleyHttp.API_DELETE_POST + poster.id, object : VolleyHandler {
            override fun onSuccess(response: String?) {
                Logger.d("put", response!!)
                //tv_responce.text = response
            }

            override fun onError(error: String?) {
                Logger.d("put", error!!)
                //tv_responce.text = error
            }

        })
    }
//for retrofit
    fun workWithRetrofit(poster: Poster) {
        //posterList()
        //createPost(poster)
        //updatePost(poster)
        //deletePost(poster)
    }

    fun deletePost(poster: Poster) {
        RetrofitHttp.posterService.deletePost(poster.id).enqueue(object : Callback<PosterResp> {
            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                Logger.d("@@@", response.body().toString())
            }

            override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                Logger.e("@@@", t.message.toString())
            }

        })
    }


    fun updatePost(poster: Poster) {
        RetrofitHttp.posterService.updatePost(poster.id, poster)
            .enqueue(object : Callback<PosterResp> {
                override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                    Logger.d("@@@", response.body().toString())
                }

                override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                    Logger.e("@@@", t.message.toString())
                }

            })
    }

    fun createPost(poster: Poster) {
        RetrofitHttp.posterService.createPost(poster).enqueue(object : Callback<PosterResp> {
            override fun onResponse(call: Call<PosterResp>, response: Response<PosterResp>) {
                Logger.d("@@@", response.body().toString())
            }

            override fun onFailure(call: Call<PosterResp>, t: Throwable) {
                Logger.e("@@@", t.message.toString())
            }

        })

    }

    fun posterList() {
        RetrofitHttp.posterService.listPost().enqueue(object : Callback<ArrayList<PosterResp>> {
            override fun onResponse(
                call: Call<ArrayList<PosterResp>>,
                response: Response<ArrayList<PosterResp>>
            ) {
                Logger.d("@@@", response.body().toString())
            }

            override fun onFailure(call: Call<ArrayList<PosterResp>>, t: Throwable) {
                Logger.e("@@@", t.message.toString())
            }

        })
    }

}