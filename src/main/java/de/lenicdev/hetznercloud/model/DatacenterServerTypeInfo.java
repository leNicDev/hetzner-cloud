package de.lenicdev.hetznercloud.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;

@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = PUBLIC_ONLY, setterVisibility = NONE)
public class DatacenterServerTypeInfo {

    private List<Long> supported;
    private List<Long> available;
    @JsonProperty("available_for_migration")
    private List<Long> availableForMigration;


    public List<Long> getSupported() {
        return supported;
    }

    public List<Long> getAvailable() {
        return available;
    }

    public List<Long> getAvailableForMigration() {
        return availableForMigration;
    }

}
