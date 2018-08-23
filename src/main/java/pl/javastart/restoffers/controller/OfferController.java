package pl.javastart.restoffers.controller;


import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import pl.javastart.restoffers.repository.CategoryDAO;
import pl.javastart.restoffers.repository.OfferDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class OfferController {

    private OfferDAO offerDAO;
    private CategoryDAO categoryRepository;

    public OfferController(OfferDAO offerDAO, CategoryDAO categoryRepository) {
        this.offerDAO = offerDAO;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("api/offers")
    public List<OfferDTO> getOffers(@RequestParam(required = false) String title) {
        List<OfferDTO> result = new ArrayList<>();
        List<Offer> offers = new ArrayList<>();

        if (StringUtils.isEmpty(title)) {
            offers = offerDAO.getAll();
        } else {
            offers = offerDAO.find(title);
        }

        for (Offer offer : offers) {
            OfferDTO dto = new OfferDTO(offer.getId(), offer.getTitle(), offer.getDescription(), offer.getImgUrl(), offer.getPrice(), offer.getCategory().getName());
            result.add(dto);
        }
        return result;
    }

    @GetMapping("/api/offers/{id}")
    public OfferDTO getOffer(@PathVariable long id) {
        Offer offer = offerDAO.findById(id);
        OfferDTO dto = new OfferDTO(offer.getId(), offer.getTitle(), offer.getDescription(), offer.getImgUrl(), offer.getPrice(), offer.getCategory().getName());
        return dto;
    }

    @PostMapping("/api/offers")
    public void saveOffer(@RequestBody OfferDTO offerDTO) {
        Category category = categoryRepository.findByName(offerDTO.getCategory());
        Offer newOffer = new Offer(new Random().nextInt(), offerDTO.getTitle(), offerDTO.getDescription(), offerDTO.getImgUrl(), offerDTO.getPrice(), category);
        offerDAO.persist(newOffer);
    }

    @GetMapping("/api/offers/count")
    public int offersCount() {
        final int offerNumber = offerDAO.getAll().size();
        return offerNumber;
    }

    @GetMapping("/api/categories/names")
    public List<String> getCategoryNames() {
        List<String> result = new ArrayList<>();
        for (Category category : categoryRepository.getAll()) {
            result.add(category.getName());
        }
        return result;
    }

    @GetMapping("/api/categories")
    public List<CategoryDTO> getCategories() {
        List<CategoryDTO> result = new ArrayList<>();
        for (Category category : categoryRepository.getAll()) {
            CategoryDTO dto = new CategoryDTO(category.getName(), category.getDescription(), category.getOfferList().size());
            result.add(dto);
        }
        return result;
    }

    @PostMapping("/api/categories")
    public void saveCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = new Category(categoryDTO.getName(), categoryDTO.getDescription());
        categoryRepository.persist(category);
    }


    @DeleteMapping("/api/offers/{id}")
    public void removeOffer(@PathVariable long id){

        offerDAO.remove(id);
    }

    @DeleteMapping("/api/categories/{id}")
    public void removeCategory(@PathVariable long id){
        categoryRepository.remove(id);
    }
}