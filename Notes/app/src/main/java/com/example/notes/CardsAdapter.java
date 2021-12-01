package com.example.notes;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;



public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardViewHolder> {
    //далее добавляем все, что ему нужно по подсказкам

    public static final String TAG="CardAdapter";
    private final CardSource source;
    private final Activity activity;
    private onCardClickListener clickListener;
    private int menuPosition = -1;


    public int getMenuPosition() {
        return menuPosition;
    }


    public CardsAdapter(Activity activity,CardSource source) {
        this.source = source;
        this.activity=activity;
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
        holder.bind(source.getCard(position));
        Log.d(TAG, "onBindViewHolder" + position);
    }

    //спрашивает сколько элементов нужно в списке
    // его можно обманывать, довалять и удалять элементы
    @Override
    public int getItemCount() {
        return source.size();
    }
    //RecyclerView.Adapter джинерик и ему нежен класс холдер



    class CardViewHolder extends RecyclerView.ViewHolder{
        TextView textView= itemView.findViewById(R.id.text_view);


        public CardViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    menuPosition = getLayoutPosition();
                    return false;
                }
            });
            activity.registerForContextMenu(itemView);
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

