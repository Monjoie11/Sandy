package com.learning.sandwich.sandy.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.learning.sandwich.sandy.model.Sandwich;
import java.util.List;

/**
 * This public interface contains the methods that will interact with Room's sandwich database.
 * Sometimes it whispers my name late at night.
 */
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

  String TRAINING_QUERY =
      "SELECT "
          + "    * "
          + "FROM "
          + "    Sandwich "
          + "WHERE "
          + "    NOT image_resource "
          + "    AND sandwich_style NOT IN ( "
          + "        SELECT "
          + "            sandwich_style "
          + "        FROM  "
          + "            Sandwich  "
          + "        WHERE  "
          + "            sandwich_id <= 12  "
          + "            AND NOT human_eat  "
          + "            AND sandwich_id NOT IN (4, 8, 12) "
          + "    )";

  /**
   * @param sandwich this method will insert a sandwich in the sandwich entity
   */
  @Insert
  void insert(Sandwich... sandwich);


  /**
   * @return this method will return a list of all sandwiches in the database wrapped around live
   * data
   */
  @Query("Select * FROM sandwich")
  LiveData<List<Sandwich>> getAll();

  /**
   * @return ths method returns a list of all sandwiches whose image reference is a URL and not a
   * drawable resource and whose category is not one designated non-sandwich by the user
   */
  @Query(TRAINING_QUERY)
  LiveData<List<Sandwich>> getAllForTraining();


  /**
   * @param sandwichId this method searches for a specific sandwich by id
   */
  @Query("SELECT * FROM sandwich WHERE sandwich_id = :sandwichId")
  LiveData<Sandwich> findById(Long sandwichId);

  /**
   * @param sandwiches this method deletes one or more sandwiches from the database. It is used as a
   * punishment in some third-world countries
   */
  @Delete
  void delete(Sandwich... sandwiches);

  /**
   * @return this method is the "pruning method. It uses the constant PRUNING_QUERY to search for
   * all sandwiches with the style a user determined not be sandwiches during the tutorial and
   * deletes them. This is doe in advance of the sending to Clarifai
   */
  @Query(PRUNING_QUERY)
  int tutorialDelete();

  /**
   * @param sandwiches this method updates a sandwiches status in the database in reference to
   * whether or not the user thinks it is a sandwich
   */
  @Update
  int updateHumanEat(Sandwich... sandwiches);

}
