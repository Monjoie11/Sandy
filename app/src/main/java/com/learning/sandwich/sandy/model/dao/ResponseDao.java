package com.learning.sandwich.sandy.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.learning.sandwich.sandy.model.Response;
import java.util.List;

@Dao
public interface ResponseDao {

  @Insert
  long insert(Response response);

  @Query("Select * FROM response")
  LiveData<List<Response>> getAll();

}
