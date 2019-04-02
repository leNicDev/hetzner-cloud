package de.lenicdev.hetznercloud.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.math.BigDecimal;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;

@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = PUBLIC_ONLY, setterVisibility = NONE)
public class Price {

    private BigDecimal net;
    private BigDecimal gross;


    public BigDecimal getNet() {
        return net;
    }

    public BigDecimal getGross() {
        return gross;
    }

}
