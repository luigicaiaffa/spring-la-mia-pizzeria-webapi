package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Ingredient;
import org.lessons.java.spring_la_mia_pizzeria_crud.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping
    public String index(Model model, @RequestParam(name = "name", required = false) String name) {
        List<Ingredient> ingredients;

        if (name != null && !name.isEmpty()) {
            ingredients = ingredientService.findByName(name);
        } else {
            ingredients = ingredientService.findAll();
        }

        model.addAttribute("ingredients", ingredients);
        return "/ingredients/index";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("ingredient", new Ingredient());
        return "/ingredients/create-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("ingredient") Ingredient formIngredient, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            return "/ingredients/create-edit";
        } else {
            ingredientService.create(formIngredient);
            return "redirect:/ingredients";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {

        model.addAttribute("edit", true);
        model.addAttribute("ingredient", ingredientService.getById(id));
        return "/ingredients/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("ingredient") Ingredient formIngredient,
            BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            model.addAttribute("ingredient", ingredientService.getById(id));
            return "/ingredients/create-edit";
        } else {
            ingredientService.update(formIngredient);
            return "redirect:/ingredients";
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        ingredientService.deleteById(id);
        return "redirect:/ingredients";
    }

}
