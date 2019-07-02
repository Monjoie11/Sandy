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
  SharedPreferences.Editor editor;
  int tutorialPosition;


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
    responseText = view.findViewById(R.id.response_text);//I'm going to attempt to use some logic to differentiate the functions of the buttons:
    if (!sharedPref.getBoolean(getString(R.string.saved_tutorial_complete_key), false)) {
      responseText.setText(R.string.first_screen);
      ImageButton yesButton = view.findViewById(R.id.yes_button);
      yesButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View viewYes1) {
          doTutorial();
        }
      });

      ImageButton noButton = view.findViewById(R.id.no_button);
      noButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View viewNo1) {
          Toast.makeText(context, "Watch where you put that finger. I said touch my buns.",
              Toast.LENGTH_LONG).show();
        }
      });// I got this to work using that handy context I created.
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

  private void doTutorial() {
    final int[] imageArray = {R.drawable.test_image1, R.drawable.test_image2, R.drawable.test_image3,
        R.drawable.test_image4, R.drawable.test_image5, R.drawable.test_image6, R.drawable.test_image7,
        R.drawable.test_image8, R.drawable.test_image9, R.drawable.test_image10, R.drawable.test_image11,
        R.drawable.test_image12};

    ImageButton yesButton = getView().findViewById(R.id.yes_button);
//    for(tutorialPosition = 0; tutorialPosition <= 12; ) {
//      if (tutorialPosition < 12) {
        responseImage = getView().findViewById(R.id.captured_sandwich);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), imageArray[tutorialPosition]);
        responseImage.setImageBitmap(bmp);
        yesButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            responseText.setText(randomTutorialQuestions());
            tutorialPosition++;
          }
        });
        //add on no click
    //  }
      //change tutorial status to now complete using shared preferences
   // }
  }


  public Boolean getTutorialComplete() {
    return tutorialComplete;
  }

  public void setTutorialComplete(Boolean tutorialComplete) {
    this.tutorialComplete = tutorialComplete;
  }


}




