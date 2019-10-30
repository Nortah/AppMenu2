package com.example.appmenu.database;

import android.app.Application;

import com.example.appmenu.database.repository.TypeRepository;

public class BaseApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this);
    }

    public TypeRepository getTypeRepository() {
        return TypeRepository.getInstance();
    }

}
