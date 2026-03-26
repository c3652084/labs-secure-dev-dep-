package com.example.hospitalapp.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hospitalapp.R;
import com.example.hospitalapp.data.Appointment;
import com.example.hospitalapp.data.AppointmentDao;
import com.example.hospitalapp.data.DatabaseProvider;
import com.example.hospitalapp.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppointmentActivity extends AppCompatActivity {

    private EditText etPatientName, etClinic, etDate, etTime;
    private TextView tvAppointments;
    private AppointmentDao appointmentDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        etPatientName = findViewById(R.id.etPatientName);
        etClinic = findViewById(R.id.etClinic);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        tvAppointments = findViewById(R.id.tvAppointments);

        Button btnBook = findViewById(R.id.btnBook);
        Button btnLoadApi = findViewById(R.id.btnLoadApi);

        appointmentDao = DatabaseProvider.getDatabase(this).appointmentDao();

        btnBook.setOnClickListener(v -> bookAppointment());
        btnLoadApi.setOnClickListener(v -> loadMockApi());
    }

    private void bookAppointment() {
        String patientName = etPatientName.getText().toString().trim();
        String clinic = etClinic.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String time = etTime.getText().toString().trim();

        if (patientName.isEmpty() || clinic.isEmpty() || date.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, "Fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        List<Appointment> existing = appointmentDao.getAppointmentsByDate(date);
        for (Appointment appointment : existing) {
            if (appointment.time.equals(time) && appointment.clinic.equalsIgnoreCase(clinic)) {
                Toast.makeText(this, "Conflict detected for this clinic and time", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        appointmentDao.insertAppointment(new Appointment(patientName, clinic, date, time));
        tvAppointments.setText("Appointment booked for " + patientName + " at " + time);
        Toast.makeText(this, "Appointment booked", Toast.LENGTH_SHORT).show();
    }

    private void loadMockApi() {
        RetrofitClient.getApiService().getAppointments().enqueue(new Callback<List<Appointment>>() {
            @Override
            public void onResponse(Call<List<Appointment>> call, Response<List<Appointment>> response) {
                tvAppointments.setText("Mock API call completed. Response code: " + response.code());
            }

            @Override
            public void onFailure(Call<List<Appointment>> call, Throwable t) {
                tvAppointments.setText("Network handled gracefully: " + t.getMessage());
            }
        });
    }
}
