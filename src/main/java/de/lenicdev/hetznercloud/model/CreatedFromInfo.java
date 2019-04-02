package de.lenicdev.hetznercloud.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;

@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = PUBLIC_ONLY, setterVisibility = NONE)
public class CreatedFromInfo {

    private Long id;
    private String name;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
