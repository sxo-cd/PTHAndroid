package me.passtheheadphones.data.remote;

import android.content.Context;
import android.util.Log;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.CookieHandler;
import java.net.Proxy;
import java.util.List;

import me.passtheheadphones.PTHApplication;
import me.passtheheadphones.data.model.Index;
import me.passtheheadphones.data.model.Profile;
import me.passtheheadphones.data.remote.interceptors.AddCookiesInterceptor;
import me.passtheheadphones.data.remote.interceptors.ReceivedCookiesInterceptor;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Single;

/**
 * Created by stu on 6/12/16.
 */

public interface ApiService {

    String ENDPOINT = PTHApplication.DEFAULT_SITE + "/";
    @FormUrlEncoded
    @Headers("User-Agent: WhatAndroid Android")
    @POST("login.php")
    Single<ResponseBody> login(@Field("username") String username, @Field("password") String password, @Field("keeplogged") int keepLogged);

    @Headers({"User-Agent: WhatAndroid Android"})
    @GET("ajax.php?action=index")
    Single<Index> index();

    @Headers({"User-Agent: WhatAndroid Android"})
    @GET("ajax.php?action=user")
    Single<Profile> profile(@Query("id") int userId, @Query("auth") String auth);

    @Headers({"User-Agent: WhatAndroid Android"})
    @GET("ajax.php?action=user_recents")
    Single<Profile> recents(@Query("userId") String userId, @Query("limit") String limit, @Query("auth") String auth);

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static ApiService newApiService(Context applicationContext) {
            CookieJar cookieJar =
                    new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(applicationContext));

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create();
            Retrofit retrofit = new Retrofit.Builder().client(new OkHttpClient.Builder()
                    .cookieJar(cookieJar)
                    .addInterceptor(new AddCookiesInterceptor(applicationContext))
                    .addInterceptor(new ReceivedCookiesInterceptor(applicationContext))
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build())
                    .baseUrl(ApiService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(ApiService.class);
        }
    }
}

