package com.example.appmenu.database;

import android.os.AsyncTask;
import android.util.Log;

import com.example.appmenu.database.entity.TypeEntity;

public class DatabaseInitializer
{
    public static final String TAG = "DatabaseInitializer";

    public static void populateDatabase(final AppDatabase db) {
        Log.i(TAG, "Inserting demo data.");
        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }

    private static void addType(final AppDatabase db, final String name, final String image) {
        TypeEntity type = new TypeEntity(name, image);
        db.typeDao().insert(type);
    }

    private static void populateWithTestData(AppDatabase db) {
        db.typeDao().deleteAll();

        addType(db, "Burger", "pathToBurger");
        addType(db, "Burger", "pathToBurger");
        addType(db, "Burger", "pathToBurger");
        addType(db, "Burger", "pathToBurger");
    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase database;

        PopulateDbAsync(AppDatabase db) {
            database = db;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            populateWithTestData(database);
            return null;
        }

    }
}
