package com.learning.sandwich.sandy;

import android.content.Context;
import android.content.res.Resources;
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
              Resources res = context.getResources();
              new Thread(() -> {
                for(int i = 1; i < 13; i++) { //TODO Amake sure to expand scope of  for-loop if images added to tutorial
                  Sandwich sandwich = new Sandwich();
                  sandwich.setSandwichId(i);
                  sandwich.setFileName("test_image" + i);
                  sandwich.setMachineEat(true);
                  sandwich.setSandwichStyle(0);
                  sandwich.setImageResource(true);
                  INSTANCE.sandwichDao().insert(sandwich);
                }
              }).start();
            }
          })
          .build();
    }
    return INSTANCE;
  }

}
