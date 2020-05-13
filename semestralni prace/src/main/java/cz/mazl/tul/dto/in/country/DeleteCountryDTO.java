package cz.mazl.tul.dto.in.country;

public class DeleteCountryDTO {
    private String iso;
    private String countryName;

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "DeleteCountryDTO{" +
                "iso='" + iso + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
