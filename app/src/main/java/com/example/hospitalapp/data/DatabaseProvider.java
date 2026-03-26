package com.example.hospitalapp.data;

import android.content.Context;

import androidx.room.Room;

public class DatabaseProvider {
    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "hospital_database"
            ).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}
