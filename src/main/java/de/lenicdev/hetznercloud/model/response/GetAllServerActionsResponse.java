package de.lenicdev.hetznercloud.model.response;

import de.lenicdev.hetznercloud.model.Action;

import java.util.List;

public class GetAllServerActionsResponse extends MetaResponse {

    private List<Action> actions;


    public List<Action> getActions() {
        return actions;
    }

    public void setActions(List<Action> actions) {
        this.actions = actions;
    }

}
