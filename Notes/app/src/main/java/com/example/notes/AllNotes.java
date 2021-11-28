package com.example.notes;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.Arrays;
import java.util.List;

import static android.icu.text.DisplayContext.LENGTH_SHORT;


public class AllNotes extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         View view= inflater.inflate(R.layout.fragment_all_notes, container, false);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getActivity().findViewById(R.id.create).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                  FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, new CreateNote());
                fragmentTransaction.addToBackStack(null);
                 fragmentTransaction.commit();

            }
        });

        getActivity().findViewById(R.id.close).setOnClickListener(v -> {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Закрыть")
                    .setMessage("Вы действительно хотите закрыть приложение?")
                    .setPositiveButton("Нет",((dialog, which) -> {

                    }))
                    .setNegativeButton("Да", ((dialog, which) -> {
                         Context context = getActivity();
                         Toast.makeText(context, "Пока", Toast.LENGTH_SHORT).show();
                         System.exit(0);
                    }))
                    .show();

        });

        List<Card> cardList = Arrays.asList(
                new Card (getString(R.string.title1),getString(R.string.description1),R.drawable.winter1),
                new Card (getString(R.string.title2),getString(R.string.description2),R.drawable.winter2),
                new Card (getString(R.string.title3),getString(R.string.description3),R.drawable.winter3),
                new Card (getString(R.string.title4),getString(R.string.description4),R.drawable.winter3),
                new Card (getString(R.string.title5),getString(R.string.description5),R.drawable.winter5),
                new Card (getString(R.string.title6),getString(R.string.description6),R.drawable.winter6),
                new Card (getString(R.string.title7),getString(R.string.description7),R.drawable.winter7)
        );
        // ищем и заполняем наш RecyclerView
        RecyclerView recyclerView = getActivity().findViewById(R.id.recycler_view);
        CardsAdapter adapter = new CardsAdapter(cardList);
        adapter.setCardClickListener((v,position)->{

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container, new OpenNote());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();


        });

        recyclerView.setAdapter(adapter);
        //далее создаем класс адапрера
    }


}

