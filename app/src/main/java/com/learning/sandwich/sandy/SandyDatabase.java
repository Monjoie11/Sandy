package com.learning.sandwich.sandy;

import android.content.Context;
import android.os.AsyncTask;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.learning.sandwich.sandy.model.Response;
import com.learning.sandwich.sandy.model.Sandwich;
import com.learning.sandwich.sandy.model.dao.ResponseDao;
import com.learning.sandwich.sandy.model.dao.SandwichDao;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

@Database(entities = {Sandwich.class, Response.class}, version = 1)
public abstract class SandyDatabase extends RoomDatabase {

  public static SandyDatabase INSTANCE;

  public abstract SandwichDao sandwichDao();

  public abstract ResponseDao responseDao();

  public static SandyDatabase getInstance(Context context) {
    if (INSTANCE == null) {
      INSTANCE = Room
          .databaseBuilder(context.getApplicationContext(), SandyDatabase.class, "sandwiches_room")
          .addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
              super.onCreate(db);
              new PopulateDbTask(INSTANCE, context).execute();
            }
          })
          .build();
    }
    return INSTANCE;
  }

  private static class PopulateDbTask extends AsyncTask<Void, Void, Void> {

    private final SandyDatabase db;

    private final Context context;

    PopulateDbTask(SandyDatabase db, Context context) {
      this.db = db;
      this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
      Gson gson = new GsonBuilder().create();

      InputStream inputSandwich = context.getResources().openRawResource(R.raw.json_sandwiches);
      Reader readerSandwich = new InputStreamReader(inputSandwich);
      Sandwich[] sandwiches = gson.fromJson(readerSandwich, Sandwich[].class);
      db.sandwichDao().insert(sandwiches);

      return null;
    }
  }
}