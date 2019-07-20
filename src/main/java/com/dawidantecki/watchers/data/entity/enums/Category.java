package com.dawidantecki.watchers.data.entity.enums;

public enum Category {
    ACTION("Action"),
    FAMILY("Family"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DRAMA("Drama"),
    EPIC("Epic"),
    FANTASY("Fantasy"),
    HISTORICAL("Historical"),
    HORROR("Horror"),
    MUSICAL("Musical"),
    ROMANCE("Romance"),
    SCIENCE_FICTION("Science Fiction"),
    THRILLER("Thriller"),
    WESTERN("Western");

    private String type;

    Category(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
