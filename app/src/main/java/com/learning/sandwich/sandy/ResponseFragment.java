package com.learning.sandwich.sandy;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.navigation.Navigation;
import android.preference.PreferenceManager;
import java.util.Random;

public class ResponseFragment extends Fragment {


  private ImageButton yesButton;
  private ImageButton noButton;
  private Random rng = new Random();
  private ResponseViewModel mViewModel;
  private ImageView responseImage;
  private TextView responseText;
  private Context context;
  private SharedPreferences sharedPref;
  private Boolean tutorialComplete;
  private SharedPreferences.Editor editor;
  private int tutorialPosition = 0;
  final int[] imageArray = {R.drawable.test_image1, R.drawable.test_image2,
      R.drawable.test_image3,
      R.drawable.test_image4, R.drawable.test_image5, R.drawable.test_image6,
      R.drawable.test_image7,
      R.drawable.test_image8, R.drawable.test_image9, R.drawable.test_image10,
      R.drawable.test_image11,
      R.drawable.test_image12};


  public ResponseFragment() {
    // Required (because I copied straight outta at the movies) empty public constructor
  }

  public static ResponseFragment newInstance() {
    return new ResponseFragment();
  } // I'm wondering why I put this here? Getting an instance does seem important


  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    this.context = context;
    sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
    editor = sharedPref.edit();
  } // again outta movies, but context also seems super important


  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.response_fragment, container, false);
    responseImage = view.findViewById(R.id.captured_sandwich);
    Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.test_image1);
    responseImage.setImageBitmap(bmp);
    noButton = view.findViewById(R.id.no_button);
    noButton.setOnClickListener(v -> {
      if (tutorialPosition == 0 && !sharedPref.getBoolean(getString(R.string.saved_tutorial_complete_key), false)) {
        Toast.makeText(context, "Watch where you put that finger. I said touch my buns.",
            Toast.LENGTH_LONG).show();
      } else if(tutorialPosition > 0 && tutorialPosition < 12){
        Toast.makeText(context, "Not a sandwich - discarding recipe",
            Toast.LENGTH_LONG).show();
        //TODO update response value in room
        doTutorial(tutorialPosition++);
      } else if(tutorialPosition == 12) {
        Toast.makeText(context, "Discarding recipe. TUTORIAL COMPLETE",
            Toast.LENGTH_LONG).show();
        //TODO update response value in room
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(getString(R.string.saved_tutorial_complete_key), true);
        editor.apply();
        tutorialPosition++;
      }
      // TODO Do other stuff
    });
    yesButton = view.findViewById(R.id.yes_button);
    yesButton.setOnClickListener(v -> {
      if (tutorialPosition == 0 && !sharedPref.getBoolean(getString(R.string.saved_tutorial_complete_key), false)) {
        Toast.makeText(context, "Mmmmmm. Teach me your ways. Starting Tutorial",
            Toast.LENGTH_LONG).show();
        doTutorial(tutorialPosition++);
      } else if(tutorialPosition > 0 && tutorialPosition < 12){
        Toast.makeText(context, "Adding recipe to repertoire",
            Toast.LENGTH_LONG).show();
        //TODO update response value in room
        doTutorial(tutorialPosition++);
      } else if(tutorialPosition == 12) {
        Toast.makeText(context, "Final recipe added. TUTORIAL COMPLETE",
            Toast.LENGTH_LONG).show();
        //TODO update response value in room
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(getString(R.string.saved_tutorial_complete_key), true);
        editor.apply();
        tutorialPosition++;
      }
      // Tutorial is done.
    });
    responseText = view.findViewById(R.id.response_text);
    if (!sharedPref.getBoolean(getString(R.string.saved_tutorial_complete_key), false)) {
      responseText.setText(R.string.first_screen);
    }

    return view;
  }


  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    //  mViewModel = ViewModelProviders.of(this).get(ResponseViewModel.class);
    // TODO: Use the ViewModel
  }

  private String randomTutorialQuestions() {
    String[] answers = getResources().getStringArray(R.array.tutorial_questions);
    return answers[rng.nextInt(answers.length)];
  }

  private void doTutorial(int position) {
    responseText.setText(randomTutorialQuestions());
    Bitmap bmp = BitmapFactory.decodeResource(getResources(), imageArray[position]);
    responseImage.setImageBitmap(bmp);
    yesButton.setVisibility(View.VISIBLE);
    noButton.setVisibility(View.VISIBLE);
  }


  public Boolean getTutorialComplete() {
    return tutorialComplete;
  }

  public void setTutorialComplete(Boolean tutorialComplete) {
    this.tutorialComplete = tutorialComplete;
  }


}




