package com.example.appmenu.database.repository;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.appmenu.database.AppDatabase;
import com.example.appmenu.database.BaseApp;
import com.example.appmenu.database.async.CreateType;
import com.example.appmenu.database.async.DeleteType;
import com.example.appmenu.util.OnAsyncEventListener;
import com.example.appmenu.database.async.UpdateType;
import com.example.appmenu.database.entity.TypeEntity;

import java.util.List;

public class TypeRepository
{
    private static TypeRepository instance;

    private TypeRepository() {}

    public static TypeRepository getInstance() {
        if (instance == null) {
            synchronized (TypeRepository.class) {
                if (instance == null) {
                    instance = new TypeRepository();
                }
            }
        }
        return instance;
    }

    public LiveData<TypeEntity> getType(final int id, Context context) {
        return AppDatabase.getInstance(context).typeDao().getById(id);
    }
    public LiveData<List<TypeEntity>> getAllTypes(Application application)
    {
        return ((BaseApp) application).getDatabase().typeDao().getAll();
    }

    public void insert(final TypeEntity type, OnAsyncEventListener
            callback, Context context) {
        new CreateType(context, callback).execute(type);
    }
    public void update(final TypeEntity type, OnAsyncEventListener
            callback, Context context) {
        new UpdateType(context, callback).execute(type);
    }
    public void delete(final TypeEntity type, OnAsyncEventListener
            callback, Context context) {
        new DeleteType(context, callback).execute(type);
    }
}

