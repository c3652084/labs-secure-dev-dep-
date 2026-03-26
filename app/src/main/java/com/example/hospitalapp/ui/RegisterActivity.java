package com.example.hospitalapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.example.hospitalapp.R;
import com.example.hospitalapp.data.DatabaseProvider;
import com.example.hospitalapp.data.Patient;
import com.example.hospitalapp.data.PatientDao;
import com.example.hospitalapp.security.SecurityUtils;
import com.example.hospitalapp.utils.ValidationUtils;

public class RegisterActivity extends AppCompatActivity {

    private EditText etNhsNumber, etFullName, etDob, etContact;
    private TextView tvResult;
    private PatientDao patientDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNhsNumber = findViewById(R.id.etNhsNumber);
        etFullName = findViewById(R.id.etFullName);
        etDob = findViewById(R.id.etDob);
        etContact = findViewById(R.id.etContact);
        tvResult = findViewById(R.id.tvResult);

        Button btnRegister = findViewById(R.id.btnRegister);
        Button btnBiometric = findViewById(R.id.btnBiometric);
        Button btnAppointments = findViewById(R.id.btnAppointments);
        Button btnEhr = findViewById(R.id.btnEhr);

        patientDao = DatabaseProvider.getDatabase(this).patientDao();

        btnRegister.setOnClickListener(v -> registerPatient());
        btnBiometric.setOnClickListener(v -> authenticateBiometric());
        btnAppointments.setOnClickListener(v -> startActivity(new Intent(this, AppointmentActivity.class)));
        btnEhr.setOnClickListener(v -> startActivity(new Intent(this, EhrActivity.class)));
    }

    private void registerPatient() {
        String nhsNumber = etNhsNumber.getText().toString().trim();
        String fullName = etFullName.getText().toString().trim();
        String dob = etDob.getText().toString().trim();
        String contact = etContact.getText().toString().trim();

        boolean valid = true;
        if (!ValidationUtils.isValidNhsNumber(nhsNumber)) {
            etNhsNumber.setError("Enter a valid 10-digit NHS number");
            valid = false;
        }
        if (!ValidationUtils.isValidFullName(fullName)) {
            etFullName.setError("Enter a valid full name");
            valid = false;
        }
        if (!ValidationUtils.isValidDob(dob)) {
            etDob.setError("Use DD/MM/YYYY");
            valid = false;
        }
        if (!ValidationUtils.isValidContact(contact)) {
            etContact.setError("Enter a valid phone number or email");
            valid = false;
        }
        if (!valid) return;

        if (patientDao.getPatientByNhsNumber(nhsNumber) != null) {
            etNhsNumber.setError("NHS number already exists");
            return;
        }

        patientDao.insertPatient(new Patient(nhsNumber, fullName, dob, contact));
        SharedPreferences prefs = SecurityUtils.getSecurePrefs(this);
        prefs.edit().putString("last_registered_nhs", nhsNumber).apply();

        tvResult.setText("Patient registered successfully.");
        Toast.makeText(this, "Saved to Room database", Toast.LENGTH_SHORT).show();
        clearForm();
    }

    private void clearForm() {
        etNhsNumber.setText("");
        etFullName.setText("");
        etDob.setText("");
        etContact.setText("");
    }

    private void authenticateBiometric() {
        BiometricPrompt prompt = new BiometricPrompt(
                this,
                ContextCompat.getMainExecutor(this),
                new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                        Toast.makeText(RegisterActivity.this, "Biometric login success", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Hospital App Login")
                .setSubtitle("Use your fingerprint or device credential")
                .setNegativeButtonText("Cancel")
                .build();

        prompt.authenticate(promptInfo);
    }
}
