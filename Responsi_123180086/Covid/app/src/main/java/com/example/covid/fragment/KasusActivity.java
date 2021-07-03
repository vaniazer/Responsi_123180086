package com.example.covid.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covid.R;
import com.example.covid.adapter.KasusAdapter;
import com.example.covid.model.kasus.ContentItem;
import com.example.covid.service.CovidApi;
import com.example.covid.service.CovidListener;

import java.util.ArrayList;
import java.util.List;

public class KasusActivity extends Fragment {

    RecyclerView rvKasus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_kasus, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvKasus = view.findViewById(R.id.rv_kasus_list);

        new CovidApi().getKasus(kasusListener);

    }

    CovidListener<List<ContentItem>> kasusListener = new CovidListener<List<ContentItem>>(){

        @Override
        public void onSuccess(List<ContentItem> items) {

            ArrayList<ContentItem> data = new ArrayList<>();

            for(int i = 0; i < items.size(); i++){
                if(!items.get(i).getTanggal().contains("Qualifying")){
                    data.add(items.get(i));
                }
                Log.d("Hasil : ", items.get(i).getTanggal());

            }

            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rvKasus.setLayoutManager(linearLayoutManager);
            KasusAdapter rvAdapter = new KasusAdapter(data);
            rvKasus.setAdapter(rvAdapter);

        }

        @Override
        public void onFailed(String msg) {
            Log.d("ISI ERROR", msg);
        }
    };
}