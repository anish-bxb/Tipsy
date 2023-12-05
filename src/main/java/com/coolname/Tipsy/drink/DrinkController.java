package com.coolname.Tipsy.drink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping(path = "api/v1/drink")
@CrossOrigin(origins = "http://localhost:5173")
public class DrinkController {
    private final DrinkService drinkService;
    @Autowired
    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @GetMapping
    public List<Drink> getDrink(){
        return drinkService.getAllDrinks();
    }

    @GetMapping("/ingredients")
    public List<String> getIngredients(){
        return drinkService.getIngredients();
    }
    @GetMapping("/by_ingredients")
    public List<Drink> getDrinkByIngredient(@RequestParam(value = "ingredient") List<String> ingredients){
        return drinkService.getDrinkByIngredient(ingredients);
    }
}

