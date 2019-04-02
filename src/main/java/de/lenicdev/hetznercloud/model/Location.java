package de.lenicdev.hetznercloud.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;

@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = PUBLIC_ONLY, setterVisibility = NONE)
public class Location {

    private Long id;
    private String name;
    private String description;
    private String country;
    private String city;
    private Double latitude;
    private Double longitude;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

}
