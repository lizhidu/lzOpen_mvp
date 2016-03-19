package com.lizhidu.lzopen.rest;

import android.util.Log;

import com.lizhidu.lzopen.bean.NewsResult;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by dulzh on 3/15/16.
 */
public class RestClient {

    private static NetInterface netInterface;
    private static String baseUrl = "https://api.github.com";
    private static String baseUrl1 = "http://op.juhe.cn";

    public static NetInterface getNetInterface() {
        if (netInterface == null) {
//            okhttp<3.0拦截器添加方式
//            OkHttpClient client = new OkHttpClient();
//            client.interceptors().add(new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//                    Response response = chain.proceed(chain.request());
//                    return response;
//                }
//            });

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Response response = chain.proceed(chain.request());
                            Log.d("json_responseData",""+response);
                            return response;
                        }
                    })
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl1)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            netInterface = retrofit.create(NetInterface.class);
        }

        return netInterface;
    }
}
