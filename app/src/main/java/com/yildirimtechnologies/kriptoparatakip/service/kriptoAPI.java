package com.yildirimtechnologies.kriptoparatakip.service;

import com.yildirimtechnologies.kriptoparatakip.model.KriptoModeli;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface kriptoAPI {
    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    Call<List<KriptoModeli>> getData();

}
