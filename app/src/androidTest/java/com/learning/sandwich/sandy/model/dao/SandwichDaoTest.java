package com.learning.sandwich.sandy.model.dao;

import static org.junit.Assert.*;

import android.content.Context;
import android.text.method.MovementMethod;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.filters.SmallTest;
import com.learning.sandwich.sandy.SandyDatabase;
import com.learning.sandwich.sandy.model.Sandwich;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runners.MethodSorters;


@SmallTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)//to set test ordr you can stick z's, etc on methods
public class SandwichDaoTest {




  //make ststic to change the fact that db is town down after every test
  private static SandyDatabase db;
  private static SandwichDao dao;
  private long sandwichId;


  @Rule
  public TestRule rule = new InstantTaskExecutorRule();

  @BeforeClass
  public static void setup() throws Exception{
    Context context = ApplicationProvider.getApplicationContext();
    db = Room.inMemoryDatabaseBuilder(context, SandyDatabase.class).build();
    dao = db.sandwichDao();
  }

  @Test
  public void insert() {
    Sandwich sandwich = new Sandwich();
    sandwich.setSandwichStyle(1);
    sandwich.setSandwichId(101);
    sandwich.setMachineEat(true);
    sandwich.setHumanEat(false);
//    sandwichId = dao.insert(sandwich);
    assertTrue(sandwichId > 0);
  }

  //we are testing to see if an exception is thrown when a null value is implemented
  @Test(expected = Exception.class)
  public void insertNullTitle() {
    Sandwich sandwich = new Sandwich();
    //long id = dao.insert(sandwich);
    fail("this shouldn't get here");
  }

  @Test
  public void findById() {
  }

  //baecuse I'm using livedata, witchcraft must be used
  @Test
  public void postInsertFindById() {
    Sandwich sandwich = dao.findById(sandwichId).getValue();
    assertNotNull(sandwich);
    assertEquals(1, sandwich.getSandwichStyle());
  }

  @After
  public static void tearDown() throws Exception{
    db.close();
  }

}

//package edu.cnm.deepdive.atthemovies.util;
//sepearate class in Main SRC

//    import androidx.annotation.Nullable;
//    import androidx.lifecycle.LiveData;
//    import androidx.lifecycle.Observer;
//    import java.util.concurrent.CountDownLatch;
//    import java.util.concurrent.TimeUnit;
//
//public class LiveDataTestUtil {
//
//  public static <T> T getValue(LiveData<T> liveData) throws InterruptedException {
//    final Object[] data = new Object[1];
//    CountDownLatch latch = new CountDownLatch(1);
//    Observer<T> observer = new Observer<T>() {
//      @Override
//      public void onChanged(@Nullable T o) {
//        data[0] = o;
//        latch.countDown();
//        liveData.removeObserver(this);
//      }
//    };
//    liveData.observeForever(observer);
//    latch.await(2, TimeUnit.SECONDS);
//
//    return (T) data[0];
//  }
//
//}

//package edu.cnm.deepdive.atthemovies.model.dao;
//
//    import static org.junit.Assert.assertEquals;
//    import static org.junit.Assert.assertNotNull;
//    import static org.junit.Assert.assertTrue;
//    import static org.junit.Assert.fail;
//
//    import android.content.Context;
//    import android.database.sqlite.SQLiteConstraintException;
//    import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
//    import androidx.room.Room;
//    import androidx.test.core.app.ApplicationProvider;
//    import androidx.test.filters.SmallTest;
//    import edu.cnm.deepdive.atthemovies.model.entity.Movie;
//    import edu.cnm.deepdive.atthemovies.model.entity.Movie.Genre;
//    import edu.cnm.deepdive.atthemovies.service.MoviesDatabase;
//    import edu.cnm.deepdive.atthemovies.util.LiveDataTestUtil;
//    import org.junit.AfterClass;
//    import org.junit.BeforeClass;
//    import org.junit.FixMethodOrder;
//    import org.junit.Rule;
//    import org.junit.Test;
//    import org.junit.runners.MethodSorters;
//
//@SmallTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class MovieDaoTest {
//
//  @Rule
//  public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();
//
//  private static MoviesDatabase db;
//  private static MovieDao dao;
//  private static long movieId;
//
//  @BeforeClass
//  public static void setup() throws Exception {
//    Context context = ApplicationProvider.getApplicationContext();
//    db = Room.inMemoryDatabaseBuilder(context, MoviesDatabase.class)
//        .allowMainThreadQueries()
//        .build();
//    dao = db.movieDao();
//  }
//
//  @Test
//  public void insert() {
//    Movie movie = new Movie();
//    movie.setGenre(Genre.ACTION);
//    movie.setTitle("Leon: The Professional");
//    movie.setScreenwriter("Luc Besson");
//    movieId = dao.insert(movie);
//    assertTrue(movieId > 0);
//  }
//
//  @Test(expected = SQLiteConstraintException.class)
//  public void insertNullTitle() {
//    Movie movie = new Movie();
//    dao.insert(movie);
//    fail("This shouldn't get here!");
//  }
//
//  @Test
//  public void postInsertFindById() throws InterruptedException {
//    Movie movie = LiveDataTestUtil.getValue(dao.findById(movieId));
//    assertNotNull(movie);
//    assertEquals("Leon: The Professional", movie.getTitle());
//  }
//
//  @AfterClass
//  public static void tearDown() throws Exception {
//    db.close();
//  }
//
//}

