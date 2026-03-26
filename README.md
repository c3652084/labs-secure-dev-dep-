# Hospital Management Mobile Application

## Module

COM6003 – Secure Software Development
Leeds Trinity University

---

# Project Overview

This project is a mobile hospital management application developed as part of the COM6003 laboratory coursework. The aim of the project is to demonstrate the development of a secure Android application while implementing core healthcare management functionality.

The application allows hospital staff to register patients, schedule appointments, store electronic health records and apply security protections to sensitive medical information.

The system was developed using **Android Studio with Java** and integrates several Android frameworks for secure data storage, authentication and database management.

---

# Technologies Used

The following technologies and libraries were used in the development of the application:

* Java
* Android Studio
* Room Database (SQLite)
* Retrofit API Client
* ZXing Barcode Scanner
* Android Biometric Authentication
* Android Security Crypto Library
* JUnit Testing Framework

---

# Lab 1 – Patient Registration

The first laboratory task focuses on implementing patient registration functionality within the application.

### Features implemented

* Patient registration form
* NHS number validation
* Input validation for user fields
* Secure data storage using Room database
* Error handling for invalid input

### Patient information stored

* NHS number
* Full name
* Date of birth
* Contact information

This lab establishes the core database structure used throughout the rest of the application.

---

# Lab 2 – Appointment Scheduling and Authentication

The second laboratory task extends the application by introducing appointment scheduling and authentication features.

### Features implemented

* Appointment booking system
* Conflict detection for appointments
* Biometric authentication using fingerprint login
* API communication using Retrofit
* Network error handling

This functionality allows staff to manage appointments while maintaining secure authentication for system access.

---

# Lab 3 – Electronic Health Records (EHR)

The third laboratory task focuses on clinical data management through electronic health records.

### Features implemented

* Recording of patient clinical information
* Storage of vital signs including heart rate, temperature and glucose
* Allergy recording
* Barcode scanning for patient identification
* Data storage using Room database

This lab simulates how hospital staff can manage and retrieve patient medical information securely.

---

# Lab 4 – Security Implementation

The final laboratory task focuses on improving the security of the application.

### Security features implemented

* Encrypted SharedPreferences for secure data storage
* Secure handling of sensitive patient information
* Input validation to prevent invalid or malicious data
* Parameterised database queries to reduce injection risks

These measures demonstrate secure software development practices within a healthcare application environment.

---

# Application Screenshots

### Patient Registration

![Patient Registration](screenshots/registration_screen.png)

The registration interface allows staff to enter patient information and validates NHS numbers before storing them securely in the Room database.

---

### Appointment Scheduling

![Appointment Booking](screenshots/appointment_booking.png)

Staff can create appointments while the system checks for scheduling conflicts to prevent double bookings.

---

### Biometric Authentication

![Biometric Login](screenshots/biometric_login.png)

The system supports fingerprint authentication to ensure only authorised staff can access sensitive medical information.

---

### Electronic Health Records

![EHR Screen](screenshots/ehr_screen.png)

Clinicians can record patient health data including allergies, heart rate, temperature and glucose readings.

---

### Barcode Scanning

![Barcode Scanner](screenshots/barcode_scan.png)

Barcode scanning enables quick patient identification and retrieval of medical records.

---

# Testing

Unit tests were implemented to validate the input validation utilities used within the application.

### Tests performed

* NHS number format validation
* Date of birth format validation
* Contact information validation

Testing was implemented using the **JUnit testing framework**.

---

# How to Run the Application

1. Clone the repository

git clone https://github.com/c3652084/labs-secure-dev-dep-.git

2. Open the project in **Android Studio**

3. Allow **Gradle** to synchronise dependencies

4. Run the application using an **Android emulator or a physical device**

---

# Author
Haseeb Lodhi Level 6 
Student submission for the **COM6003 Secure Software Development** module at Leeds Trinity University.
