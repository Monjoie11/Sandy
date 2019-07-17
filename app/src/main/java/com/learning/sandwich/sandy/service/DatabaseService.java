package com.learning.sandwich.sandy.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;



public class DatabaseService extends SQLiteOpenHelper {



    public DatabaseService(@Nullable Context context) {
      super(context, "json_sandwiches.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
      db.execSQL("CREATE TABLE sandwiches ("
          + "sandwich_id INTEGER PRIMARY KEY, "
          + "sandwich_style INTEGER,"
          + "machine_eat INTEGER,"
          + "file_name TEXT,"
          + "human_eat INTEGER) " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      // Do Nothing
    }



  }


