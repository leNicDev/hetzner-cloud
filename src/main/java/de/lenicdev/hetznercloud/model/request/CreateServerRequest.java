package de.lenicdev.hetznercloud.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class CreateServerRequest {

    private String name;
    @JsonProperty("server_type")
    private String serverType;
    private String location;
    @JsonProperty("start_after_create")
    private Boolean startAfterCreate;
    private String image;
    @JsonProperty("ssh_keys")
    private List<String> sshKeys;
    private List<Long> volumes;
    @JsonProperty("user_data")
    private String userData;
    private Boolean automount;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getStartAfterCreate() {
        return startAfterCreate;
    }

    public void setStartAfterCreate(Boolean startAfterCreate) {
        this.startAfterCreate = startAfterCreate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getSshKeys() {
        return sshKeys;
    }

    public void setSshKeys(List<String> sshKeys) {
        this.sshKeys = sshKeys;
    }

    public List<Long> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<Long> volumes) {
        this.volumes = volumes;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public Boolean getAutomount() {
        return automount;
    }

    public void setAutomount(Boolean automount) {
        this.automount = automount;
    }

}
