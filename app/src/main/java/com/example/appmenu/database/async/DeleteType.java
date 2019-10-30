package com.example.appmenu.database.async;

import android.content.Context;
import android.os.AsyncTask;

import com.example.appmenu.database.AppDatabase;
import com.example.appmenu.database.entity.TypeEntity;
import com.example.appmenu.util.OnAsyncEventListener;

public class DeleteType extends AsyncTask<TypeEntity, Void, Void> {
    private AppDatabase database;
    private OnAsyncEventListener callback;
    private Exception exception;

    public DeleteType(Context context,
                      OnAsyncEventListener callback) {
        database = AppDatabase.getInstance(context);
        this.callback = callback;
    }

    @Override
    protected Void doInBackground(TypeEntity... params) {
        try {
            for (TypeEntity type : params)
                database.typeDao().delete(type);
        } catch (Exception e) {
            exception = e;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        if (callback != null) {
            if (exception == null) {
                callback.onSuccess();
            } else {
                callback.onFailure(exception);
            }
        }

    }
}
