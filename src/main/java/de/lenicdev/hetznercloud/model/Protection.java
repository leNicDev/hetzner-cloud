package de.lenicdev.hetznercloud.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;

@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = PUBLIC_ONLY, setterVisibility = NONE)
public class Protection {

    private Boolean delete;
    private Boolean rebuild;


    public Boolean getDelete() {
        return delete;
    }

    public Boolean getRebuild() {
        return rebuild;
    }

}
