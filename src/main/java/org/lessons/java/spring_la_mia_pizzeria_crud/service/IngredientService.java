package org.lessons.java.spring_la_mia_pizzeria_crud.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> findAll() {
        return ingredientRepository.findAll();
    }

    public List<Ingredient> findByName(String name) {
        return ingredientRepository.findByNameContaining(name);
    }

    public Ingredient getById(Integer id) {
        Optional<Ingredient> ingredientAttempt = ingredientRepository.findById(id);

        if (ingredientAttempt.isEmpty()) {
            //
        }

        return ingredientAttempt.get();
    }

    public Ingredient create(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public Ingredient update(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public void delete(Ingredient ingredient) {

        for (Pizza linkedPizza : ingredient.getPizzas()) {
            linkedPizza.getIngredients().remove(ingredient);
        }

        ingredientRepository.delete(ingredient);
    }

    public void deleteById(Integer id) {
        Ingredient ingredient = getById(id);
        
        for (Pizza linkedPizza : ingredient.getPizzas()) {
            linkedPizza.getIngredients().remove(ingredient);
        }

        ingredientRepository.delete(ingredient);
    }

    public Boolean existsById(Integer id) {
        return ingredientRepository.existsById(id);
    }

    public Boolean exists(Ingredient ingredient) {
        return ingredientRepository.existsById(ingredient.getId());
    }

}
