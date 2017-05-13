package com.myniotech.moneycontrol.rest;

import com.myniotech.moneycontrol.dto.SpentSync;
import com.myniotech.moneycontrol.model.spent.Spent;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by luiz on 25/04/17.
 */

public interface SpentService {

    @GET("spent")
    Observable<SpentSync> getSpents();

    @POST("spent")
    Observable<Response<ResponseBody>> postSpent(@Body Spent spent);

    @DELETE("spent/{id}")
    Observable<Response<ResponseBody>> deleteSpent(@Path("id") String id);
}
