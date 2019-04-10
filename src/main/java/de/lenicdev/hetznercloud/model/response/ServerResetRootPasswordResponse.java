package de.lenicdev.hetznercloud.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServerResetRootPasswordResponse extends ActionResponse {

    @JsonProperty("root_password")
    private String rootPassword;


    public String getRootPassword() {
        return rootPassword;
    }

    public void setRootPassword(String rootPassword) {
        this.rootPassword = rootPassword;
    }

}
