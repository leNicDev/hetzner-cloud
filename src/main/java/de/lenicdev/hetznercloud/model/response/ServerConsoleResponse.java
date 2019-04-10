package de.lenicdev.hetznercloud.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ServerConsoleResponse extends ActionResponse {

    @JsonProperty("wss_url")
    private String wssUrl;
    private String password;


    public String getWssUrl() {
        return wssUrl;
    }

    public void setWssUrl(String wssUrl) {
        this.wssUrl = wssUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
