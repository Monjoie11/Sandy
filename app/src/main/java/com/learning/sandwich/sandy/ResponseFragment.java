package com.learning.sandwich.sandy;

import android.content.Context;
import android.widget.Button;
import android.widget.ImageButton;
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

public class ResponseFragment extends Fragment {

  private ImageButton yesButton;
  private ImageButton noButton;
  private ResponseViewModel mViewModel;
  private TextView responseText;
  private Context context;
  private Boolean tutorialComplete;

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
  } // again outta movies, but context also seems super important


  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    final View view = inflater.inflate(R.layout.response_fragment, container, false);
    responseText = view.findViewById(R.id.response_text);//I'm going to attempt to use some logic to differentiate the functions of the buttons

    responseText.setText(R.string.first_screen);
    ImageButton yesButton = view.findViewById(R.id.yes_button);
    yesButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View viewYes1) {
        /*SandwichImageFragment sandwichImageFragment = new SandwichImageFragment();
        FragmentManager manager  = getFragmentManager();
        manager.beginTransaction().replace(R.id.action_responseFragment_to_sandwichImageFragment, sandwichImageFragment, sandwichImageFragment.getTag()).commit();*/
        Navigation.findNavController(view).navigate(R.id.action_responseFragment_to_sandwichImageFragment);
      }
    });

    ImageButton noButton = view.findViewById(R.id.no_button);
    noButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View viewNo1) {
        Toast.makeText(context, "Watch where you put that finger. I said touch my buns.", Toast.LENGTH_LONG).show();
      }
    });// I got this to work using that handy context I created.

    return view;
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    mViewModel = ViewModelProviders.of(this).get(ResponseViewModel.class);
    // TODO: Use the ViewModel
  }
}




