package com.example.notes;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class AllNotes extends Fragment{

    private CardSource source;
    private CardsAdapter adapter;
    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

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


        // ищем и заполняем наш RecyclerView
        RecyclerView recyclerView = getActivity().findViewById(R.id.recycler_view);
        source= new CardSourceImp(context);

        adapter = new CardsAdapter(getActivity(),source);
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

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
   }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_add) {
            source.addCard(
                    new Card("Новая карточка", " описание", R.drawable.winter3)
            );
            adapter.notifyItemInserted(source.size()-1);
            return true;
        } else if(item.getItemId()==R.id.action_clear){
            int size = source.size();
            source.clearCards();
            adapter.notifyItemRangeRemoved(0, size);
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    // регистрация
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.card_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_delete){
            // по какой позиции удалять
            source.deleteCard(adapter.getMenuPosition());
            return true;
        }else if(item.getItemId() ==R.id.action_update){
            source.updateCard(adapter.getMenuPosition(),
                    new Card("Новая карточка", " описание", R.drawable.winter3));
            return true;
        }
        return super.onContextItemSelected(item);
    }
}

