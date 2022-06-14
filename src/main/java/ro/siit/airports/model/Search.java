package ro.siit.airports.model;

public class Search {

    private String country;
    private String city;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean hasCountry() {
        return country != null && !country.isEmpty();
    }

    public boolean hasCity() {
        return city != null && !city.isEmpty();
    }
}
