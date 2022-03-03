package dev.matyaqubov.networking

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import dev.matyaqubov.networking.Retrofit.PosterResp
import dev.matyaqubov.networking.Retrofit.RetrofitHttp
import dev.matyaqubov.networking.Volley.VolleyHandler
import dev.matyaqubov.networking.Volley.VolleyHttp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var tv_responce: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        tv_responce = findViewById(R.id.tv_responce)
        val poster = Poster(1, 1, "PDP", "Online")

        //for Volley
        workWithVolley(poster)

        //for Retrofit
        workWithRetrofit(poster)


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
                tv_responce.text = response
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
                    tv_responce.text = response
                }

                override fun onError(error: String?) {
                    Logger.d("post", error!!)
                    tv_responce.text = error
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
                    tv_responce.text = response
                }

                override fun onError(error: String?) {
                    Logger.d("put", error!!)
                    tv_responce.text = error
                }

            })
    }

    fun del(poster: Poster) {

        VolleyHttp.del(VolleyHttp.API_DELETE_POST + poster.id, object : VolleyHandler {
            override fun onSuccess(response: String?) {
                Logger.d("put", response!!)
                tv_responce.text = response
            }

            override fun onError(error: String?) {
                Logger.d("put", error!!)
                tv_responce.text = error
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