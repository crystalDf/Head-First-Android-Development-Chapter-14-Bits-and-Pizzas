package com.star.bitsandpizzas;


public class Pasta {

    private String name;
    private int imageResourceId;

    private static String[] pastaNames = {"Spaghetti Bolognese", "Lasagne"};

    public static final Pasta[] pastas = {
            new Pasta(pastaNames[0], R.drawable.diavolo),
            new Pasta(pastaNames[1], R.drawable.funghi)
    };

    public Pasta(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
