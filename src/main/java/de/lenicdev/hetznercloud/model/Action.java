package de.lenicdev.hetznercloud.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Date;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.*;

@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = PUBLIC_ONLY, setterVisibility = NONE)
public class Action {

    private Long id;
    private String command;
    private String status;
    private Integer progress;
    private Date started;
    private Date finished;
    private List<Resource> resources;
    private Error error;


    public Long getId() {
        return id;
    }

    public String getCommand() {
        return command;
    }

    public String getStatus() {
        return status;
    }

    public Integer getProgress() {
        return progress;
    }

    public Date getStarted() {
        return started;
    }

    public Date getFinished() {
        return finished;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public Error getError() {
        return error;
    }

}
