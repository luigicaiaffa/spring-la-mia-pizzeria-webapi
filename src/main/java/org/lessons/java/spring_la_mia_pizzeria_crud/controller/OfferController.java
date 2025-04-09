package org.lessons.java.spring_la_mia_pizzeria_crud.controller;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Offer;
import org.lessons.java.spring_la_mia_pizzeria_crud.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("offer") Offer formOffer, BindingResult bindingResult, Model model) {
    
        if (bindingResult.hasErrors()) {
            return "/offers/create-edit";
        } else {
            offerService.create(formOffer);
            return "redirect:/pizzas/" + formOffer.getPizza().getId();
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try {
            Offer offer = offerService.getById(id);
            model.addAttribute("edit", true);
            model.addAttribute("offer", offer);

        } catch (EntityNotFoundException e) {
            model.addAttribute("offer", null);
        }

        return "/offers/create-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("offer") Offer formOffer,
            BindingResult bindingResult, Model model) {  
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            model.addAttribute("offer", offerService.getById(id));
            return "/offers/create-edit";
        } else {
            offerService.update(formOffer);
            return "redirect:/pizzas/" + formOffer.getPizza().getId();
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Offer offer = offerService.getById(id);
        
        offerService.delete(offer);
        return "redirect:/pizzas/" + offer.getPizza().getId();
    }

}
