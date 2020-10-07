package com.xeon.mitrakurirlangitf.API;

import com.xeon.mitrakurirlangitf.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    @GET("retrieve.php")
    Call<ResponseModel> ardRetriveData();

    @FormUrlEncoded
    @POST("create.php")
    Call<ResponseModel> ardCreateData(
      @Field("nama") String nama,
      @Field("alamat") String alamat,
      @Field("Telepon") String Telepon
    );
}
