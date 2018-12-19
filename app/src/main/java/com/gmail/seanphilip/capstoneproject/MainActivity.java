package com.gmail.seanphilip.capstoneproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.gmail.seanphilip.capstoneproject.Fragments.AboutFragment;
import com.gmail.seanphilip.capstoneproject.Fragments.DictionaryFragment;
import com.gmail.seanphilip.capstoneproject.Fragments.HomeFragment;
import com.gmail.seanphilip.capstoneproject.Fragments.PlacesFragment;
import com.gmail.seanphilip.capstoneproject.Fragments.TranslatorFragment;

public class MainActivity extends AppCompatActivity {

    public ActionBar toolbar;
    // private TextView mTextMessage;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment = null;
            toolbar = getSupportActionBar();
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    toolbar.setTitle("Tourist Assistance App");
                    break;
                case R.id.navigation_translator:
                    fragment = new TranslatorFragment();
                    toolbar.setTitle("Translator");
                    break;
                case R.id.navigation_touristspots:
                    fragment = new PlacesFragment();
                    toolbar.setTitle("Places");
                    break;
                case R.id.navigation_dictionary:
                    fragment = new DictionaryFragment();
                    toolbar.setTitle("Dictionary");
                    break;
                case R.id.navigation_aboutus:
                    fragment = new AboutFragment();
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

        // mTextMessage = findViewById(R.id.message);

        //loading the default fragment
        loadFragment(new HomeFragment());

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
}
