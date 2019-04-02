package de.lenicdev.hetznercloud.model.exception;

public class HetznerCloudException extends Exception {

    private String errorCode;
    private String errorDetails;


    public HetznerCloudException(String errorMessage, String errorCode, String errorDetails) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorDetails = errorDetails;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDetails() {
        return errorDetails;
    }

}
