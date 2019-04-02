package de.lenicdev.hetznercloud.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;

@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = PUBLIC_ONLY, setterVisibility = NONE)
public class Image {

    private Long id;
    private String type;
    private String status;
    private String name;
    private String description;
    @JsonProperty("image_size")
    private Double imageSize;
    @JsonProperty("disk_size")
    private Integer diskSize;
    private Date created;
    @JsonProperty("created_from")
    private CreatedFromInfo createdFrom;
    @JsonProperty("bound_to")
    private Long boundTo;
    @JsonProperty("os_flavor")
    private String osFlavor;
    @JsonProperty("os_version")
    private String osVersion;
    @JsonProperty("rapid_deploy")
    private Boolean rapidDeploy;
    private Protection protection;
    private Date deprecated;
    private Map<String, String> labels;


    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getImageSize() {
        return imageSize;
    }

    public Integer getDiskSize() {
        return diskSize;
    }

    public Date getCreated() {
        return created;
    }

    public CreatedFromInfo getCreatedFrom() {
        return createdFrom;
    }

    public Long getBoundTo() {
        return boundTo;
    }

    public String getOsFlavor() {
        return osFlavor;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public Boolean getRapidDeploy() {
        return rapidDeploy;
    }

    public Protection getProtection() {
        return protection;
    }

    public Date getDeprecated() {
        return deprecated;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

}
