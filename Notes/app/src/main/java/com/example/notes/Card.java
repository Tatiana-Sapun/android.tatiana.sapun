package com.example.notes;

import androidx.annotation.IdRes;

public class Card {
    private final String title;
    private final String description;
    @IdRes
    private final int image;
    private final  boolean like;

    public Card(String title, String description, int image, boolean like) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.like = like;
    }

    public Card(String title, String description, int image) {
        this(title,description, image, false);
    }

    public String getTitle() { return title; }

    public String getDescription() { return description; }

    public int getImage() { return image; }

    public boolean isLike() { return like; }
}
