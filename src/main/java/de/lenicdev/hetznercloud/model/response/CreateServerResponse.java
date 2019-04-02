package de.lenicdev.hetznercloud.model.response;

import de.lenicdev.hetznercloud.model.Action;

import java.util.List;

public class CreateServerResponse {

    private String server;
    private Action action;
    private List<Action> nextActions;
    private String rootPassword;

}
