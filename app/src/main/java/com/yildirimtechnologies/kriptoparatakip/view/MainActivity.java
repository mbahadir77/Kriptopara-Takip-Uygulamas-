package com.yildirimtechnologies.kriptoparatakip.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yildirimtechnologies.kriptoparatakip.R;
import com.yildirimtechnologies.kriptoparatakip.adapter.RecyclerViewAdapter;
import com.yildirimtechnologies.kriptoparatakip.model.KriptoModeli;
import com.yildirimtechnologies.kriptoparatakip.service.kriptoAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<KriptoModeli> kriptoModelis;
    private String BASE_URL = "https://raw.githubusercontent.com/";
    Retrofit retrofit;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json
        //retrofit ve json
        recyclerView = findViewById(R.id.recyclerView);
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        veriyükleme();
    }

    private void veriyükleme(){
        kriptoAPI kriptoAPI = retrofit.create(kriptoAPI.class);
        Call<List<KriptoModeli>> call = kriptoAPI.getData();
        call.enqueue(new Callback<List<KriptoModeli>>() {
            @Override
            public void onResponse(Call<List<KriptoModeli>> call, Response<List<KriptoModeli>> response) {
                if (response.isSuccessful()) {
                    List<KriptoModeli> responselist = response.body();
                    kriptoModelis = new ArrayList<>(responselist);

                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter = new RecyclerViewAdapter(kriptoModelis);
                    recyclerView.setAdapter(recyclerViewAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<KriptoModeli>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}