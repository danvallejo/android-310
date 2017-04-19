package com.cstructor.android310;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherService {
    @GET("weather")
    Call<WeatherModel> getWeather(@Query("q") String city, @Query("appId") String appId);
}
