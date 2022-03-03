package dev.matyaqubov.networking.Retrofit.Service

import dev.matyaqubov.networking.Poster
import dev.matyaqubov.networking.Retrofit.PosterResp
import retrofit2.Call
import retrofit2.http.*

interface PosterService {
    //charset=UTF-8
    @Headers("Content-type:application/json")
    @GET("posts")
    fun listPost():Call<ArrayList<PosterResp>>
    @GET("posts/{id}")
    fun singlePost(@Path("id") id:Int):Call<PosterResp>

    @POST("posts")
    fun createPost(@Body post:Poster):Call<PosterResp>

    @PUT("posts/{id}")
    fun updatePost(@Path("id")id:Int,@Body post: Poster):Call<PosterResp>

    @DELETE("posts/{id}")
    fun deletePost(@Path("id") id: Int,): Call<PosterResp>
}