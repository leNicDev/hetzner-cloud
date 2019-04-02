package de.lenicdev.hetznercloud.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;

@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = PUBLIC_ONLY, setterVisibility = NONE)
public class LocationPrice {

    private String location;
    @JsonProperty("price_hourly")
    private Price priceHourly;
    @JsonProperty("price_monthly")
    private Price priceMonthly;


    public String getLocation() {
        return location;
    }

    public Price getPriceHourly() {
        return priceHourly;
    }

    public Price getPriceMonthly() {
        return priceMonthly;
    }

}
