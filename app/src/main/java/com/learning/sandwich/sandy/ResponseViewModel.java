package com.learning.sandwich.sandy;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.learning.sandwich.sandy.model.Sandwich;
import com.learning.sandwich.sandy.model.dao.SandwichDao;
import java.util.List;

/**
 * The view model can be thought of as the duct work in and out of a given fragment using different
 * threads, database tasks, live data (reactive coding) or some combination thereof; in this case,
 * all of the above.
 */
public class ResponseViewModel extends AndroidViewModel {

  private SandyDatabase db;
  private SandwichDao dao;

  /**
   * @param application this constructor gets an instance of the database and dao for use in the
   * below methods that will interact with fragments or livedata
   */
  public ResponseViewModel(@NonNull Application application) {
    super(application);
    db = SandyDatabase.getInstance(application);
    dao = db.sandwichDao();
  }

  /**
   * @return this method gets a list of all sandwiches in the database.
   */
  public LiveData<List<Sandwich>> getSandwiches() {
    return dao.getAll();
  }

  /**
   * @param sandwich this method updates attribute designating whether or not the user identified an
   * object as a sandwich
   */
  public void updateHumanEat(final Sandwich sandwich) {

    new Thread(() -> dao.updateHumanEat(sandwich)).start();

  }

  /**
   * @param sandwichId Retrieves a single sandwich, in case you need a snack
   */
  public LiveData<Sandwich> getSandwich(Long sandwichId) {
    return dao.findById(sandwichId);
  }

  /**
   * @return retrieves all sandwiches not designated by the user to be of a non-sandwich category (a
   * lugubrious double negative but most accurately conveys the method's function)
   */
  public LiveData<List<Sandwich>> getTrainingSandwiches() {
    return dao.getAllForTraining();
  }

  // Notes for alter use: after on click caseR.id.picture(); new ClarafaiTask(getContext(), playerId, SceneId).execute(mFile){

}

