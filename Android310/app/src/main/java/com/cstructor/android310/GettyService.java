package com.cstructor.android310;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface GettyService {

    @Headers("Api-Key: n6q2gugumajuf4w32auet9bf")
    @GET("images")
    Call<ImagesModel> getImagesByPhrase(@Query("phrase") String phrase);
}
