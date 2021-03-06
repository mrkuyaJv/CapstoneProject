package com.gmail.seanphilip.capstoneproject.Fragments;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gmail.seanphilip.capstoneproject.R;

import java.util.Locale;

public class translatorFragment extends Fragment implements TextToSpeech.OnInitListener {
    private Button speakButton;
    private Button convertTextButton;
    private TextToSpeech tts;
    private EditText speechText;

    public translatorFragment(){
        //Empty Constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // return inflater.inflate(R.layout.translator_fragment, container, false);

        View rootView = inflater.inflate(R.layout.translator_fragment, container, false);

        convertTextButton = rootView.findViewById(R.id.convertTextButton);

        return rootView;
    }

    public void onClick(View view){
        Fragment fragment;
        switch (view.getId()){
            case R.id.convertTextButton:
                fragment = new textTranslatorFragment();
                replaceFragment(fragment);
                break;
        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tts = new TextToSpeech(getActivity(), this);
        speakButton = view.findViewById(R.id.speakButton);
        speechText = view.findViewById(R.id.speechText);
        speakButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
            }
        });
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void speakOut(){
        String text = speechText.getText().toString();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void onInit(int status){
        if(status == TextToSpeech.SUCCESS){
            int result = tts.setLanguage(Locale.getDefault());

            if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(getActivity(), "Language not supported!", Toast.LENGTH_SHORT).show();
            } else {
                speakButton.setEnabled(true);
                speakOut();
            }
        }
        else
        {
            Toast.makeText(getActivity(), "Initilization failed!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onDestroy() {
        if(tts != null){
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
