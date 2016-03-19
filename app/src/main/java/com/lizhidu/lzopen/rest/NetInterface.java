package com.lizhidu.lzopen.rest;

import com.lizhidu.lzopen.bean.NewsResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by dulzh on 3/18/16.
 */
public interface NetInterface {

    //        @Headers("User-Agent: Retrofit2.0Tutorial-App")
//        @GET("/search/users")
//        Call<GitResult> getUsersNamedTom(@Query("q") String name);

//        @POST("/user/create")
//        Call<ClipData.Item> createUser(@Body String name, @Body String email);

//        @PUT("/user/{id}/update")
//        Call<Item>Item updateUser(@Path("id") String id, @Body Item user);

        @GET("/onebox/news/query")
        Observable<NewsResult> getUsersNamedTometRes(@Query("q") String q, @Query("key") String key, @Query("dtype") String dtype);

//    @FormUrlEncoded
//    @POST("/onebox/news/query")
//    Call<NewsResult> getNews(@Query("key") String key, @Field("q") String param1, @Field("dtype") String param2);

    @FormUrlEncoded
    @POST("/onebox/news/query")
    Observable<NewsResult> getNews(@Query("key") String key, @Field("q") String param1, @Field("dtype") String param2);

    @POST("/onebox/news/query")
    void getNews(@Query("key") String key, @Field("q") String param1, @Field("dtype") String param2, Call<NewsResult> resultCallback);

}
