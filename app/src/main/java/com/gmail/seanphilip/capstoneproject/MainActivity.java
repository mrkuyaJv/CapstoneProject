package com.gmail.seanphilip.capstoneproject;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.gmail.seanphilip.capstoneproject.Fragments.aboutFragment;
import com.gmail.seanphilip.capstoneproject.Fragments.dictionaryFragment;
import com.gmail.seanphilip.capstoneproject.Fragments.homeFragment;
import com.gmail.seanphilip.capstoneproject.Fragments.placesFragment;
import com.gmail.seanphilip.capstoneproject.Fragments.translatorFragment;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity{

    public ActionBar toolbar;
    public EditText speechText;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            toolbar = getSupportActionBar();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new homeFragment();
                    toolbar.setTitle("Tourist Assistance App");
                    break;
                case R.id.navigation_translator:
                    fragment = new translatorFragment();
                    toolbar.setTitle("Translator");
                    break;
                case R.id.navigation_touristspots:
                    fragment = new placesFragment();
                    toolbar.setTitle("Places");
                    break;
                case R.id.navigation_dictionary:
                    fragment = new dictionaryFragment();
                    toolbar.setTitle("Dictionary");
                    break;
                case R.id.navigation_aboutus:
                    fragment = new aboutFragment();
                    toolbar.setTitle("About Us");
                    break;
            }
            return loadFragment(fragment);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFragment(new homeFragment());

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private boolean loadFragment(Fragment fragment){
        //switching fragment
        if(fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    public void getSpeechInput(View view){
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());


        if(i.resolveActivity(getPackageManager()) != null){
            startActivityForResult(i, 10);
        } else {
            Toast.makeText(this, "Your Device doesn't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        speechText = findViewById(R.id.speechText);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null){
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    speechText.setText(result.get(0));
                }
                break;
        }
    }
}
