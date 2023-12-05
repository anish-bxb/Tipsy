package com.coolname.Tipsy.drink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DrinkService {
    private final DrinkRepository drinkRepository;
    @Autowired
    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }
    public List<Drink> getAllDrinks(){
        return drinkRepository.findAll();
    }

    public List<Drink> getDrinkByIngredient(List<String> ingredients){
        List<Drink> drinks = drinkRepository.findAll();

        // Filter drinks that contain all specified ingredients
        List<Drink> filteredDrinks = new ArrayList<>();

        for (Drink drink : drinks) {
            boolean containsAllIngredients = true;

            for (String ingredient : ingredients) {
                if (!drink.getIngredients().contains(" " + ingredient)) {
                    containsAllIngredients = false;
                    break; // No need to continue checking ingredients for this drink
                }
            }

            if (containsAllIngredients) {
                filteredDrinks.add(drink);
            }
        }

        return filteredDrinks;
    }

    public List<String> getIngredients(){
        List<Drink> drinks = drinkRepository.findAll();
        Set<String> ingredients = new HashSet<String>();
        for(Drink drink : drinks){
            List<String> ingredients_ = List.of(drink.getIngredients().split(","));
            for(String ingredient:ingredients_){
                ingredients.add(ingredient.trim());
            }
        }
        ArrayList<String> sortedIngredients = new ArrayList<>(ingredients);
        Collections.sort(sortedIngredients);

        return sortedIngredients;

    }

}
