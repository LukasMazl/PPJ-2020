package cz.mazl.tul.blogic.entity.db;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T02_CITY")
public class CityEntity {

    @Id
    @Column(name = "city_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "lastUpdate", nullable = false)
    private Date lastTemperatureUpdate;

    @Column(name = "created", nullable = false)
    private Date created;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "county_id")
    private CountryEntity country;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CountryEntity getCountry() {
        return country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    public Date getLastTemperatureUpdate() {
        return lastTemperatureUpdate;
    }

    public void setLastTemperatureUpdate(Date lastTemperatureUpdate) {
        this.lastTemperatureUpdate = lastTemperatureUpdate;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
