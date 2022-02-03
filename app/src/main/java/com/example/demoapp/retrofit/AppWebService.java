package com.example.demoapp.retrofit;

import com.example.demoapp.model.ListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface AppWebService {

    @GET(Utility.URL + Utility.SUB_URL_List)
    Call<ListResponse> getList(@Query("apikey") String apikey, @Query("hash") String hash
            , @Query("ts") String ts);

    @GET(Utility.URL + Utility.SUB_URL_Details)
    Call<ListResponse> getListData(@Query("apikey") String apikey, @Query("hash") String hash
            , @Query("ts") String ts);

}