package de.lenicdev.hetznercloud.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class CreateServerRequest extends HetznerCloudRequest {

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


    public CreateServerRequest() {
        this.sshKeys = new ArrayList<>();
        this.volumes = new ArrayList<>();
    }


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


    public static class Builder {
        private String name;
        private String serverType;
        private String location;
        private Boolean startAfterCreate;
        private String image;
        private List<String> sshKeys;
        private List<Long> volumes;
        private String userData;
        private Boolean automount;


        public Builder() {
            this.sshKeys = new ArrayList<>();
            this.volumes = new ArrayList<>();
        }


        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder serverType(String serverType) {
            this.serverType = serverType;
            return this;
        }
        public Builder location(String location) {
            this.location = location;
            return this;
        }
        public Builder startAfterCreate(Boolean startAfterCreate) {
            this.startAfterCreate = startAfterCreate;
            return this;
        }
        public Builder image(String image) {
            this.image = image;
            return this;
        }
        public Builder sshKeys(List<String> sshKeys) {
            this.sshKeys = sshKeys;
            return this;
        }
        public Builder sshKey(String sshKey) {
            // Initialize sshKeys list
            if (this.sshKeys == null) {
                this.sshKeys = new ArrayList<>();
            }
            // Add new ssh key
            this.sshKeys.add(sshKey);
            return this;
        }
        public Builder volumes(List<Long> volumes) {
            this.volumes = volumes;
            return this;
        }
        public Builder volume(Long volume) {
            // Initialize volumes list
            if (this.volumes == null) {
                this.volumes = new ArrayList<>();
            }
            // Add new volume
            this.volumes.add(volume);
            return this;
        }
        public Builder userData(String userData) {
            this.userData = userData;
            return this;
        }
        public Builder automount(Boolean automount) {
            this.automount = automount;
            return this;
        }

        public CreateServerRequest build() {
            final CreateServerRequest request = new CreateServerRequest();
            request.setName(name);
            request.setServerType(serverType);
            request.setLocation(location);
            request.setStartAfterCreate(startAfterCreate);
            request.setImage(image);
            request.setSshKeys(sshKeys);
            request.setVolumes(volumes);
            request.setUserData(userData);
            request.setAutomount(automount);
            return request;
        }
    }

}
