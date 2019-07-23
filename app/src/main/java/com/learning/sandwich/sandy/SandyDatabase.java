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

/**
 * An abstract cass that creates the foundation of te ORM, which can only be intialzed be the
 * classes that call an instance thereof
 */
@Database(entities = {Sandwich.class, Response.class}, version = 1)
public abstract class SandyDatabase extends RoomDatabase {

  /**
   * The instance of the database called in Dao's and viewmodels
   */
  public static SandyDatabase INSTANCE;

  /**
   * @return An abstract field giving the Dao a reference to any SandyDatabase instance
   */
  public abstract SandwichDao sandwichDao();

  /**
   * An abstract field giving the Dao a reference to any SandyDatabase instance
   */
  public abstract ResponseDao responseDao();

  /**
   * Method that attaches a contect to a instance of the database and makes it available to other
   * application classes
   */
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

    /** Method that takes JSON object raw data and populates the ORM with it
     * @param voids
     * @return
     */
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