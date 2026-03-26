package com.example.hospitalapp.network;

import com.example.hospitalapp.data.Appointment;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("appointments")
    Call<List<Appointment>> getAppointments();
}
