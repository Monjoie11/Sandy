package com.learning.sandwich.sandy.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.learning.sandwich.sandy.model.Sandwich;
import java.util.List;

@Dao
public interface SandwichDao {


  @Insert
  void insert(Sandwich sandwich);


  @Query("Select * FROM sandwich")
  LiveData<List<Sandwich>> getAll();


  @Query("SELECT * FROM sandwich WHERE sandwich_id = :sandwichId")
  LiveData<Sandwich> findById(Long sandwichId);


}