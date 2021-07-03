package com.example.covid.service;

import com.example.covid.model.kasus.ContentItem;
import com.example.covid.model.kasus.Data;
import com.example.covid.model.kasus.KasusResponse;
import com.example.covid.model.rujukan.DataItem;
import com.example.covid.model.rujukan.RujukanResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CovidApi {

    //Membuat koneksi dengan Rest server dan konfigurasi dari link API sebagai rest server
    private Retrofit retrofit;

    private static final String URL_BASE = "https://covid19-public.digitalservice.id";

    public CovidInterface getAPI(){
        if (retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(CovidInterface.class);
    }

    //Mengambil data kasus harian
    public void getKasus(final CovidListener<List<ContentItem>> listener){
        getAPI().getKasus().enqueue(new Callback<KasusResponse>() {
            @Override
            public void onResponse(Call<KasusResponse> call, Response<KasusResponse> response) {
                KasusResponse data = response.body();
                if(data != null && data.getData() != null){
                    listener.onSuccess(data.getData().getContent());
                }
            }

            @Override
            public void onFailure(Call<KasusResponse> call, Throwable t) {
                listener.onFailed(t.getMessage());
            }
        });
    }

    //Mengambil data RS rujukan
    public void getRujukan(final CovidListener<List<DataItem>> listener){
        getAPI().getRujukan().enqueue(new Callback<RujukanResponse>() {
            @Override
            public void onResponse(Call<RujukanResponse> call, Response<RujukanResponse> response) {
                RujukanResponse data = response.body();
                if(data != null && data.getData() != null){
                    listener.onSuccess(data.getData());
                }
            }

            @Override
            public void onFailure(Call<RujukanResponse> call, Throwable t) {
                listener.onFailed(t.getMessage());
            }
        });
    }



}
