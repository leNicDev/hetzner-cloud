package de.lenicdev.hetznercloud.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.lenicdev.hetznercloud.model.Action;
import de.lenicdev.hetznercloud.model.Server;

import java.util.List;

public class CreateServerResponse {

    private Server server;
    private Action action;
    @JsonProperty("next_actions")
    private List<Action> nextActions;
    @JsonProperty("root_password")
    private String rootPassword;


    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public List<Action> getNextActions() {
        return nextActions;
    }

    public void setNextActions(List<Action> nextActions) {
        this.nextActions = nextActions;
    }

    public String getRootPassword() {
        return rootPassword;
    }

    public void setRootPassword(String rootPassword) {
        this.rootPassword = rootPassword;
    }

}
