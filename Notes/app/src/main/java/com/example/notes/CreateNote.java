package com.example.notes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Locale;
import java.util.Locale.Category;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNote extends Fragment {

    private String noteName;
    private String noteText;
    private TextView textView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       return inflater.inflate(R.layout.fragment_create_note, container, false);

    }

    public void getCurrentTime(View view) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
        String strDate =  mdformat.format(calendar.getTime());
        display(strDate);
    }

    private void display(String num) {
        TextView textView = (TextView) getActivity().findViewById(R.id.current_time_view);
        textView.setText(num);
    }

}