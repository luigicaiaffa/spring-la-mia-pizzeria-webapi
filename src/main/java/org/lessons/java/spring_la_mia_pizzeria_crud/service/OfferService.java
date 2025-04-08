package org.lessons.java.spring_la_mia_pizzeria_crud.service;

import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_crud.model.Offer;
import org.lessons.java.spring_la_mia_pizzeria_crud.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    public Offer create(Offer offer) {
        return offerRepository.save(offer);
    }

    public Offer update(Offer offer) {
        return offerRepository.save(offer);
    }

    public Offer getById(Integer id) {
        Optional<Offer> offerAttempt = offerRepository.findById(id);

        if (offerAttempt.isEmpty()) {
            //
        }

        return offerAttempt.get();
    }

    public void delete(Offer offer) {
        offerRepository.delete(offer);
    }

    public void deleteById(Integer id) {
        offerRepository.deleteById(id);
    }

    public Boolean existsById(Integer id) {
        return offerRepository.existsById(id);
    }

    public Boolean exists(Offer offer) {
        return offerRepository.existsById(offer.getId());
    }

}
