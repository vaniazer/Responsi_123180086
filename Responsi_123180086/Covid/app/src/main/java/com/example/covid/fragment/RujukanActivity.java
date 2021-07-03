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
import com.example.covid.adapter.RujukanAdapter;
import com.example.covid.model.kasus.ContentItem;
import com.example.covid.model.rujukan.DataItem;
import com.example.covid.service.CovidApi;
import com.example.covid.service.CovidListener;

import java.util.ArrayList;
import java.util.List;

public class RujukanActivity extends Fragment {

    RecyclerView rvRujukan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_rujukan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvRujukan = view.findViewById(R.id.rv_rujukan_list);

        new CovidApi().getRujukan(rujukanListener);

    }

    CovidListener<List<DataItem>> rujukanListener = new CovidListener<List<DataItem>>(){

        @Override
        public void onSuccess(List<DataItem> items) {

            ArrayList<DataItem> data = new ArrayList<>();

            for(int i = 0; i < items.size(); i++){
                if(!items.get(i).getNama().contains("Qualifying")){
                    data.add(items.get(i));
                }
                Log.d("Hasil : ", items.get(i).getNama());

            }

            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rvRujukan.setLayoutManager(linearLayoutManager);
            RujukanAdapter rvAdapter = new RujukanAdapter(data);
            rvRujukan.setAdapter(rvAdapter);

        }

        @Override
        public void onFailed(String msg) {
            Log.d("ISI ERROR", msg);
        }
    };

}