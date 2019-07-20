package com.learning.sandwich.sandy.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.learning.sandwich.sandy.model.Sandwich;
import java.util.List;

@Dao
public interface SandwichDao {

String PRUNING_QUERY = 
    "DELETE FROM "
    + "    Sandwich "
    + "WHERE "
    + "    sandwich_style IN ( "
    + "        SELECT "
    + "            sandwich_style "
    + "        FROM  "
    + "            Sandwich  "
    + "        WHERE  "
    + "            sandwich_id <= 12  "
    + "            AND NOT human_eat  "
    + "            AND sandwich_id NOT IN (4, 8, 12) "
    + "    )";

  @Insert
  void insert(Sandwich... sandwich);


  @Query("Select * FROM sandwich")
  LiveData<List<Sandwich>> getAll();


  @Query("SELECT * FROM sandwich WHERE sandwich_id = :sandwichId")
  LiveData<Sandwich> findById(Long sandwichId);

  @Delete
  int delete(Sandwich... sandwiches);

  @Query(PRUNING_QUERY)
  int tutorialDelete();

  @Update
  int updateHumanEat(Sandwich... sandwiches);

}
