package com.example.notes;

public interface CardSource {
    Card getCard(int position);
    int size();
    void deleteCard(int position);
    void addCard(Card card);
    void updateCard(int position, Card card);
    void clearCards();
}