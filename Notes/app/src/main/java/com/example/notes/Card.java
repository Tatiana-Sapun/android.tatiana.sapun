package com.example.notes;

import androidx.annotation.IdRes;

public class Card {
    private final String title;
    private final String description;
    private final int image;


    public Card(String title, String description, int image) {
        this.title = title;
        this.description = description;
        this.image = image;

    }



    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public int getImage() { return image; }


}
