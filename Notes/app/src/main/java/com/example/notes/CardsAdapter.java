package com.example.notes;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardViewHolder> {
    //далее добавляем все, что ему нужно по подсказкам

    public static final String TAG="CardAdapter";
    private final List<Card> cardList;
    private onCardClickListener clickListener;


    public CardsAdapter(List<Card> cardList) {
        this.cardList = cardList;
    }

    public void setCardClickListener(onCardClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    // создание вью холдера
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //самый поростой спозоб- заинфлейтить
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item_layout,parent,false);
        CardViewHolder viewHolder = new CardViewHolder(view);
        Log.d(TAG,"onCreateViewHolder");
        return viewHolder;
    }

    //нам передается готовый холдер и задаем позицию
    @Override
    public void onBindViewHolder(@NonNull CardsAdapter.CardViewHolder holder, int position) {
        holder.bind(cardList.get(position));
        Log.d(TAG, "onBindViewHolder" + position);
    }

    //спрашивает сколько элементов нужно в списке
    // его можно обманывать, довалять и удалять элементы
    @Override
    public int getItemCount() {
        return cardList.size();
    }
    //RecyclerView.Adapter джинерик и ему нежен класс холдер

    class CardViewHolder extends RecyclerView.ViewHolder{
        TextView textView= itemView.findViewById(R.id.text_view);

        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(Card card){
            textView.setText(card.getTitle());
            textView.setOnClickListener(v -> {
                if (clickListener != null) {
                    clickListener.onCardClick(v, getAdapterPosition());
                }
            });
        }
    }

    interface onCardClickListener{
        void onCardClick(View view, int position);

    }
}
