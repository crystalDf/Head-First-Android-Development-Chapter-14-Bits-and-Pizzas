package com.star.bitsandpizzas;


public class Pizza {

    private String name;
    private int imageResourceId;

    private static String[] pizzaNames = {"Diavolo", "Funghi"};

    public static final Pizza[] pizzas = {
            new Pizza(pizzaNames[0], R.drawable.diavolo),
            new Pizza(pizzaNames[1], R.drawable.funghi)
    };

    public Pizza(String name, int imageResourceId) {
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
