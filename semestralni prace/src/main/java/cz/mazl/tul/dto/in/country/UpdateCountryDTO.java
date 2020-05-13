package cz.mazl.tul.dto.in.country;

/**
 * This class is input DTO for updating Country. UpdateCountryDTO has 3 fields where
 * originIso is iso code of country which should be updated.
 */
public class UpdateCountryDTO {

    /**
     * Origin ISO code of country
     */
    private String originIso;

    /**
     * New county iso
     */
    private String iso;

    /**
     * New country name
     */
    private String name;


    public String getOriginIso() {
        return originIso;
    }

    public void setOriginIso(String originIso) {
        this.originIso = originIso;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
