package com.star.bitsandpizzas;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PizzaMaterialFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView) inflater
                .inflate(R.layout.fragment_pizza_material, container, false);

        String[] pizzaNames = new String[Pizza.pizzas.length];
        int[] pizzaImages = new int[Pizza.pizzas.length];

        for (int i = 0; i < pizzaNames.length; i++) {
            pizzaNames[i] = Pizza.pizzas[i].getName();
        }

        for (int i = 0; i < pizzaImages.length; i++) {
            pizzaImages[i] = Pizza.pizzas[i].getImageResourceId();
        }

        CaptionedImagesAdapter captionedImagesAdapter =
                new CaptionedImagesAdapter(pizzaNames, pizzaImages);

        recyclerView.setAdapter(captionedImagesAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        return recyclerView;
    }


}
