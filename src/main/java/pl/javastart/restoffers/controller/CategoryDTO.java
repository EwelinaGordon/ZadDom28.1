package pl.javastart.restoffers.controller;

public class CategoryDTO {

    private String name;
    private String description;
    private int offers;

    public CategoryDTO() {

    }

    public CategoryDTO(String name, String description, int offers){
        this.name = name;
        this.description = description;
        this.offers = offers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOffers() {
        return offers;
    }

    public void setOffers(int offers) {
        this.offers = offers;
    }
}
