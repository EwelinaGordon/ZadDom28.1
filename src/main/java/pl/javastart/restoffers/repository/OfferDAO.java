package pl.javastart.restoffers.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.javastart.restoffers.controller.Offer;

import java.util.List;
import java.util.Optional;

@Repository
public class OfferDAO {

    @Autowired
    private OfferRepository offerRepository;


    public OfferDAO() {
    }

    public List<Offer> getAll() {
        return offerRepository.findAll();
    }

    public List<Offer> find(String title) {
        return offerRepository.findAllByTitle(title);
    }

    public Offer findById(long id) {
        Optional<Offer> result = offerRepository.findById(id);
        return result.get();
    }

    public void persist(Offer offer) {
        offerRepository.save(offer);
    }

    public void remove(long id) {
        Optional<Offer> result = offerRepository.findById(id);
        offerRepository.delete(result.get());
    }
}