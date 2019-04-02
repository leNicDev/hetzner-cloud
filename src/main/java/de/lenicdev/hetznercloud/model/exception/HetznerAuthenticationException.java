package de.lenicdev.hetznercloud.model.exception;

public class HetznerAuthenticationException extends HetznerCloudException {

    public HetznerAuthenticationException(String errorMessage, String errorCode, String errorDetails) {
        super(errorMessage, errorCode, errorDetails);
    }

}
