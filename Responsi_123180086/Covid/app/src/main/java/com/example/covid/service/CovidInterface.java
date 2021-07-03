package com.example.covid.service;

import com.example.covid.model.kasus.KasusResponse;
import com.example.covid.model.rujukan.RujukanResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidInterface {

    //Berisi metode metode yang akan digunakan dalam komunikasi data dengan rest server

    //Mendapatkan kasus harian
    @GET("/api/v1/rekapitulasi_v2/jabar/harian")
    Call<KasusResponse> getKasus();

    //Mendapatkan faskes
    @GET("/api/v1/sebaran_v2/jabar/faskes")
    Call<RujukanResponse> getRujukan();
}
