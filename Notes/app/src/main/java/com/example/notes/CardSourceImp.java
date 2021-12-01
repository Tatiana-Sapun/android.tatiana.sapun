package com.example.notes;

import android.content.Context;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardSourceImp implements  CardSource{

    List<Card> cardList;

    public CardSourceImp(@NonNull Context context) {
        this.cardList = cardList = new ArrayList<>( Arrays.asList(
                new Card (context.getString(R.string.title1),context.getString(R.string.description1),R.drawable.winter1),
                new Card (context.getString(R.string.title2),context.getString(R.string.description2),R.drawable.winter2),
                new Card (context.getString(R.string.title3),context.getString(R.string.description3),R.drawable.winter3),
                new Card (context.getString(R.string.title4),context.getString(R.string.description4),R.drawable.winter4),
                new Card (context.getString(R.string.title5),context.getString(R.string.description5),R.drawable.winter5),
                new Card (context.getString(R.string.title6),context.getString(R.string.description6),R.drawable.winter6),
                new Card (context.getString(R.string.title7),context.getString(R.string.description7),R.drawable.winter7)
        ));
    }

    @Override
    public Card getCard(int position) {
        return cardList.get(position);
    }

    @Override
    public int size() {
        return cardList.size();
    }

    @Override
    public void deleteCard(int position) {
        cardList.remove(position);
    }

    @Override
    public void addCard(Card card) {
        cardList.add(card);
    }

    @Override
    public void updateCard(int position, Card card) {
        cardList.set(position, card);
    }

    @Override
    public void clearCards() {
        cardList.clear();
    }
}

