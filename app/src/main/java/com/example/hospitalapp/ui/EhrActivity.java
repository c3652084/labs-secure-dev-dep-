package com.example.hospitalapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hospitalapp.R;
import com.example.hospitalapp.data.DatabaseProvider;
import com.example.hospitalapp.ehr.EhrDao;
import com.example.hospitalapp.ehr.EhrRecord;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class EhrActivity extends AppCompatActivity {

    private EditText etPatientName, etAllergies, etHeartRate, etTemperature, etGlucose;
    private TextView tvEhrResult;
    private EhrDao ehrDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ehr);

        etPatientName = findViewById(R.id.etEhrPatientName);
        etAllergies = findViewById(R.id.etAllergies);
        etHeartRate = findViewById(R.id.etHeartRate);
        etTemperature = findViewById(R.id.etTemperature);
        etGlucose = findViewById(R.id.etGlucose);
        tvEhrResult = findViewById(R.id.tvEhrResult);

        Button btnSaveEhr = findViewById(R.id.btnSaveEhr);
        Button btnScanBarcode = findViewById(R.id.btnScanBarcode);

        ehrDao = DatabaseProvider.getDatabase(this).ehrDao();

        btnSaveEhr.setOnClickListener(v -> saveEhr());
        btnScanBarcode.setOnClickListener(v -> new IntentIntegrator(this).initiateScan());
    }

    private void saveEhr() {
        String patientName = etPatientName.getText().toString().trim();
        if (patientName.isEmpty()) {
            etPatientName.setError("Enter patient name");
            return;
        }

        EhrRecord record = new EhrRecord(
                patientName,
                etAllergies.getText().toString().trim(),
                etHeartRate.getText().toString().trim(),
                etTemperature.getText().toString().trim(),
                etGlucose.getText().toString().trim()
        );

        ehrDao.insertEhr(record);
        tvEhrResult.setText("EHR saved for " + patientName);
        Toast.makeText(this, "Clinical record saved", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                tvEhrResult.setText("Barcode scan cancelled");
            } else {
                tvEhrResult.setText("Barcode matched patient: " + result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
