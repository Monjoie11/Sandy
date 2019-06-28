package com.learning.sandwich.sandy;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.learning.sandwich.sandy.model.Response;
import com.learning.sandwich.sandy.model.Sandwich;
import com.learning.sandwich.sandy.model.dao.ResponseDao;
import com.learning.sandwich.sandy.model.dao.SandwichDao;

@Database(entities = {Sandwich.class, Response.class}, version = 1)
public abstract class SandyDatabase extends RoomDatabase {

  public static SandyDatabase INSTANCE;

  public abstract SandwichDao sandwichDao();

  public abstract ResponseDao responseDao();

  public static SandyDatabase getInstance(Context context){
    if(INSTANCE == null){
      INSTANCE = Room.databaseBuilder(context.getApplicationContext(),SandyDatabase.class, "sandwiches_room")
          .addCallback(new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
              super.onCreate(db);
              new Thread(() -> {
                Sandwich sandwich = new Sandwich();
                sandwich.setFileName("/app/src/main/res/drawable/test_image1.png");
                sandwich.setMachineEat(true);
                sandwich.setSandwichStyle(0);
                INSTANCE.sandwichDao().insert(sandwich);
              }).start();
            }
          })
          .build();
    }
    return INSTANCE;
  }


}
