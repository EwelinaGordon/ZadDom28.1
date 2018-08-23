package pl.javastart.restoffers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.javastart.restoffers.controller.Offer;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long>{

    List<Offer> findAllByTitle(String title);
}
