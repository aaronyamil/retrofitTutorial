package com.aaronyamil.retrofittutorial.service;

import com.aaronyamil.retrofittutorial.model.RandomUser;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by AaronYamil on 4/17/2017.
 */

public interface Service {

    @GET("api/")
    Call<RandomUser> GetUserData();
}
