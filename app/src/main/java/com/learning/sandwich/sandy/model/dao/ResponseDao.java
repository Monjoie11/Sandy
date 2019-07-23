package com.learning.sandwich.sandy.model.dao;

/*
    Copyright 2019 Jeff Franken
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.learning.sandwich.sandy.model.Response;
import java.util.List;


/**
 * This public interface will contain the methods that will call Sandy's situational responses. The
 * database it will reference does not exist yet. It is sad and lonely, and I should perhaps
 * disperse it into the ether, but neither of us are ready to give up.
 */
@Dao
public interface ResponseDao {

  /**
   * @param response this method will insert responses into the database individually. It's unlikely
   * to be used as responses will be loaded by en masse via gson raw
   * @return t returns a long of the new responseId, the primary key
   */
  @Insert
  long insert(Response response);


  /**
   * Queries the response entity
   * @return returns a list of responses matching query criteria
   */
  @Query("Select * FROM response")
  LiveData<List<Response>> getAll();

}
