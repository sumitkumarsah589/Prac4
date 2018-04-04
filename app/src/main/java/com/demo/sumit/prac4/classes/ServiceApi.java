package com.demo.sumit.prac4.classes;

import com.demo.sumit.prac4.model.LoginDemoData;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Dream on 3/24/2018.
 */

public class ServiceApi {


    public static final String Url = "https://www.meetnplay.com/webservices/web_services/";
    public static final String Key = "user_login";


    public static PostService postService=null;

    public static PostService getPostService(){

        if(postService==null){
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Url).addConverterFactory(GsonConverterFactory.create()).build();
            postService = retrofit.create(PostService.class);
        }

        return postService;
    }

    public interface PostService {

        @POST(Key)
        @FormUrlEncoded
        Call<LoginDemoData> getLoginData(@Field("app_version") String app_version,
                                         @Field("unique_id") String unique_id,
                                         @Field("email") String email,
                                         @Field("password") String password,
                                         @Field("type") String type);

    }

}
