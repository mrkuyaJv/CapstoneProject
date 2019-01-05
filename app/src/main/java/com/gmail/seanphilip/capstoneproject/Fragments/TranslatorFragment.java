package com.gmail.seanphilip.capstoneproject.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gmail.seanphilip.capstoneproject.R;


public class TranslatorFragment extends Fragment {

    public TextView txtSpeechInput;
    public ImageButton speakBtn;
    private final int REQ_CODE_SPEECH_INPUT = 100;


    public TranslatorFragment(){
        //Empty Constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.translator_fragment, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
