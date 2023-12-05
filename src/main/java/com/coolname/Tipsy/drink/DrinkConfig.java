package com.coolname.Tipsy.drink;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class DrinkConfig {
    @Autowired
    private ResourceLoader resourceLoader;

    @Bean
    CommandLineRunner commandLineRunner(DrinkRepository repository) {
        return args -> {
            // Load the CSV file as a resource from the classpath
            Resource resource = resourceLoader.getResource("classpath:data.csv");

            try (CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream()))){

                String[] nextRecord;
                reader.readNext();
                while ((nextRecord = reader.readNext()) != null) {
                    String drinkName = nextRecord[0].strip();
                    String measurements = nextRecord[6].strip();
                    String instruction = nextRecord[2].strip();
                    String ingredients = nextRecord[5].toLowerCase();
                    List<String> ingredientList = List.of(ingredients.split(","));
                    ingredientList.forEach(String::strip);
                    ingredients = String.join(",", ingredientList);
                    Drink drink = new Drink(drinkName,
                            measurements + " | " + instruction,
                            ingredients);
                    repository.save(drink);
                }
            } catch (IOException e) {
                // Handle the exception appropriately (e.g., log or throw)
                e.printStackTrace();
            }
        };
    }
}

