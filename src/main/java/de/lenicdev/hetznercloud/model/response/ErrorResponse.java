package de.lenicdev.hetznercloud.model.response;

import de.lenicdev.hetznercloud.model.Error;

public class ErrorResponse {

    private Error error;


    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

}
