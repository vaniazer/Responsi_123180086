package com.example.covid.service;

public interface CovidListener<T> {
    void onSuccess(T items);
    void onFailed(String msg);
}
